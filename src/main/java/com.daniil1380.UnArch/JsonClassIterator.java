package com.daniil1380.UnArch;

public class JsonClassIterator implements ClassIterator{

    @Override
    public void iterate(Archives archives) {
        for (Archives.Archive archive : archives.getArchives()) {
            Thread threadForUnZip = new Thread(() -> {
                UnArchiverZIP unArchiver = new UnArchiverZIP(archive);
                if (unArchiver.getInputStream() != null) {
                    System.out.println("Файл найден " + archive.getSrc());
                    unArchiver.unArchive();
                }
            });
            threadForUnZip.start();
        }

    }
}
