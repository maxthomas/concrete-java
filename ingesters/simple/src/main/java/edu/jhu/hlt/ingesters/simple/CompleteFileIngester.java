/*
 * Copyright 2012-2015 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */

package edu.jhu.hlt.ingesters.simple;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.jhu.hlt.concrete.AnnotationMetadata;
import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.TextSpan;
import edu.jhu.hlt.concrete.communications.CommunicationFactory;
import edu.jhu.hlt.concrete.communications.SuperCommunication;
import edu.jhu.hlt.concrete.ingesters.base.IngestException;
import edu.jhu.hlt.concrete.ingesters.base.UTF8FileIngester;
import edu.jhu.hlt.concrete.ingesters.base.util.ExistingNonDirectoryFile;
import edu.jhu.hlt.concrete.ingesters.base.util.NotFileException;
import edu.jhu.hlt.concrete.metadata.AnnotationMetadataFactory;
import edu.jhu.hlt.concrete.util.ConcreteException;

/**
 * Implementation of {@link UTF8FileIngester} whose {@link UTF8FileIngester#fromCharacterBasedFile(Path)}
 * implementation converts the entire contents of a
 * character-based file to a {@link Communication} object.
 * <ul>
 *  <li>
 *   The file name is used as the ID of the Communication.
 *  </li>
 *  <li>
 *   The Communication will contain one {@link Section} with one {@link TextSpan}.
 *  </li>
 * </ul>
 */
public class CompleteFileIngester implements UTF8FileIngester {

  private static final Logger logger = LoggerFactory.getLogger(CompleteFileIngester.class);

  private final String kind;
  private final AnnotationMetadata md;

  /**
   * @param kind the kind of produced {@link Communication} objects
   */
  public CompleteFileIngester(String kind) {
    this.kind = kind;
    this.md = AnnotationMetadataFactory.fromCurrentLocalTime()
        .setTool("CompleteFileIngester (Kind: " + kind + ")");
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.ingesters.base.FileIngester#fromCharacterBasedFile(java.nio.file.Path, java.nio.charset.Charset)
   */
  @Override
  public Communication fromCharacterBasedFile(Path path) throws IngestException {
    try {
      ExistingNonDirectoryFile f = new ExistingNonDirectoryFile(path);
      try(InputStream is = Files.newInputStream(f.getPath());) {
        String content = IOUtils.toString(is, StandardCharsets.UTF_8);
        Communication c = CommunicationFactory.create(f.getName(), content, "Other");
        c.setType(this.getKind());
        return c;
      } catch (IOException e) {
        throw new IngestException("Caught exception reading in document.", e);
      } catch (ConcreteException e) {
        throw new IngestException(e);
      }
    } catch (NoSuchFileException | NotFileException e) {
      throw new IngestException("Path did not exist or was a directory.", e);
    }
  }

  /**
   * See usage string.
   *
   * @param args
   */
  public static void main(String[] args) {
    if (args.length != 3) {
      System.err.println("This program converts a character-based file to a .concrete file.");
      System.err.println("The text file must contain UTF-8 encoded characters.");
      System.err.println("The .concrete file will share the same name as the input file.");
      System.err.println("This program takes 3 arguments.");
      System.err.println("Argument 1: path/to/a/character/based/file");
      System.err.println("Argument 2: type of Communication to generate [e.g., tweet]");
      System.err.println("Argument 3: path/to/out/concrete/file");
      System.err.println("Example usage: " + CompleteFileIngester.class.getName()
          + " /my/text/file story /my/output/folder");
      System.exit(1);
    }

    String inPathStr = args[0];
    Path inPath = Paths.get(inPathStr);
    try {
      ExistingNonDirectoryFile ef = new ExistingNonDirectoryFile(inPath);
      Optional<String> commType = Optional.ofNullable(args[1]);
      Optional<String> outPathStr = Optional.ofNullable(args[2]);

      Path ep = ef.getPath();
      String fn = ef.getName();
      Path outPath = Paths.get(outPathStr.get());
      logger.info("Output path: {}", outPathStr);

      // Output directory exists, or it doesn't.
      // Try to create if it does not.
      if (!Files.exists(outPath)) {
        try {
          Files.createDirectories(outPath);
        } catch (IOException e) {
          logger.error("Caught exception when making output directories.", e);
        }

      // if it does, check to make sure it's a directory.
      } else {
        if (!Files.isDirectory(outPath)) {
          logger.error("Output path exists but is not a directory.");
          System.exit(1);
        } else {
          // check to make sure the output file won't be overwritten.
          Path outFile = outPath.resolve(fn + ".concrete");
          if (Files.exists(outFile)) {
            logger.warn("Output file {} exists; not overwriting.", outFile.toString());
            System.exit(1);
          }

          // 100 lines of IO error checking to run one line of code
          try {
            UTF8FileIngester ing = new CompleteFileIngester(commType.get());
            Communication comm = ing.fromCharacterBasedFile(ep);
            new SuperCommunication(comm).writeToFile(outFile, false);
          } catch (IngestException e) {
            logger.error("Caught exception during ingest.", e);
            System.exit(1);
          } catch (ConcreteException e) {
            logger.error("Caught exception writing output.", e);
          }
        }
      }
    } catch (NoSuchFileException e) {
      logger.error("Path {} does not exist.", inPathStr);
      System.exit(1);
    } catch (NotFileException e) {
      logger.error("Path {} is a directory.", inPathStr);
      System.exit(1);
    }
  }

  @Override
  public String getKind() {
    return this.kind;
  }

  @Override
  public AnnotationMetadata getMetadata() {
    return this.md;
  }
}
