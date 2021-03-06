Copyright 2012-2015 Johns Hopkins University HLTCOE. All rights
reserved.  This software is released under the 2-clause BSD license.
See LICENSE in the project root directory.

concrete-util
========

API
------------

### Serialization ###
Use this library's serialization and deserialization classes instead of rolling your own.

`TarGzCompactCommunicationSerializer` will handle almost all of your de/serialization tasks.
This class is compatible with unix `gunzip`.

#### Examples ####
```java
// your communication collection
Collection<Communication> myComms = ...

// create a TarGzCompactCommunicationSerializer
CommunicationTarGzSerializer ts = new TarGzCompactCommunicationSerializer();

// create a .tar.gz file
ts.toTarGz(myComms, "/my/home/dir/comms.tar.gz");

// create a .tar file
ts.toTar(myComms, "/my/home/dir/comms.tar");

// read from a .tar file
try (InputStream is = Files.newInputStream(Paths.get("/my/tar/file"));
     BufferedInputStream bis = new BufferedInputStream(is, 1024 * 2 * 128)) {
  Iterator<Communication iter = ts.fromTar(bis);
}

// read from a .tar.gz file
try (InputStream is = Files.newInputStream(Paths.get("/my/tar/gz/file"));
     BufferedInputStream bis = new BufferedInputStream(is, 1024 * 2 * 128)) {
  Iterator<Communication iter = ts.fromTarGz(bis);
}
```

For asynchronous programming, `CachedThreadPoolCommunicationSerializer` can assist you.
```java
try (AsyncCommunicationSerializer aser = new CachedThreadPoolCommunicationSerializer();) {
  byte[] fromOutside = ...
  Future<Communication> fc = aser.fromBytes(fromOutside);
  // other tasks
  Communication c = fc.get();

  Communication myComm = ...
  Future<byte[]> fba = aser.toBytes(myComm);
  // ...
  byte[] ba = fba.get();
}
```

concrete.X namespace
------------------------------
Everything inside the `concrete` namespace (outside `edu.jhu.hlt.concrete`)
is considered deprecated and may be deleted or changed in the future. Don't depend on it.
