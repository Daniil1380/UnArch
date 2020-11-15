package com.daniil1380.UnArch.services;

import com.daniil1380.UnArch.model.Archives;
import com.daniil1380.UnArch.services.interfaces.ArchivesUnArchiver;
import com.daniil1380.UnArch.services.interfaces.UnArchiver;

public class ArchivesListUnArchiver implements ArchivesUnArchiver {
    private final UnArchiver unArchiver;

    public ArchivesListUnArchiver(UnArchiver unArchiver){
        this.unArchiver = unArchiver;
    }

    @Override
    public void unArchiveAll(Archives archives) {
        for (Archives.Archive archive : archives.getArchives()) {
            Thread threadForUnZip = new Thread(() -> {
                System.out.println("Файл найден " + archive.getSrc());
                unArchiver.unArchive(archive.getSrc(), archive.getDst());
            });
            threadForUnZip.start();
        }
    }
}
