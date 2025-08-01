package com.example.uks.analytics;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogIngestService {

    @Autowired
    private LogRepository logRepository;

    private static final String LOG_FILE = "logs/app.log";
    private long lastFilePointer = 0;

    @PostConstruct
    public void init(){
        lastFilePointer = new File(LOG_FILE).length();
    }

    @Scheduled(fixedRate = 15000)
    public void ingestLogs(){

        try(RandomAccessFile file = new RandomAccessFile(LOG_FILE, "r")) {
            file.seek(lastFilePointer);
            String line;
            while((line = file.readLine()) != null) {
                LogDocument document = parseLine(line);


                if (document != null){
                    System.out.println("Ingested log: " + document.getMessage());
                    logRepository.save(document);
                }
            }
            lastFilePointer = file.getFilePointer();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LogDocument parseLine(String line){
        System.out.println("Parsing line: " + line);

        try{
            String[] parts = line.split(" ", 6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LogDocument document = new LogDocument();
            document.setId(UUID.randomUUID().toString());
            document.setTimestamp(LocalDateTime.parse(parts[0] + " " + parts[1], formatter));
            document.setThread(parts[2].replace("[", "").replace("]", ""));
            document.setLevel(parts[3]);
            document.setMessage(parts[5]);

            return document;
        } catch (Exception e){
            return null;
        }
    }

}
