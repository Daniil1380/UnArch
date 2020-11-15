package com.daniil1380.UnArch;

import com.daniil1380.UnArch.utils.ReaderJson;
import com.daniil1380.UnArch.model.Archives;
import com.daniil1380.UnArch.services.*;
import com.daniil1380.UnArch.services.interfaces.ArchivesUnArchiver;
import com.daniil1380.UnArch.services.interfaces.UnArchiver;

import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Archives archives = ReaderJson.readFile(scanner.nextLine());
        UnArchiver unArchiver = new UnArchiverZIP();
        ArchivesUnArchiver archivesUnArchiver = new ArchivesListUnArchiver(unArchiver);
        archivesUnArchiver.unArchiveAll(archives);
    }
}