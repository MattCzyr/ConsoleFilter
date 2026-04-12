package com.chaosthedude.consolefilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ConsoleFilterConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private static Path configFilePath;

    public static List<String> basicFilters = new ArrayList<>();
    public static List<String> regexFilters = new ArrayList<>();
    public static List<String> loggerFilters = new ArrayList<>();

    public static void load() {
        Reader reader;
        if(getFilePath().toFile().exists()) {
            try {
                reader = Files.newBufferedReader(getFilePath());

                Data data = GSON.fromJson(reader, Data.class);

                basicFilters = data.basicFilters;
                regexFilters = data.regexFilters;
                loggerFilters = data.loggerFilters;

                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        save();
    }

    public static void save() {
        try {
            Writer writer = Files.newBufferedWriter(getFilePath());
            Data data = new Data(basicFilters, regexFilters, loggerFilters);
            GSON.toJson(data, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getFilePath() {
        if (configFilePath == null) {
            configFilePath = FabricLoader.getInstance().getConfigDir().resolve(ConsoleFilter.MODID + ".json");
        }
        return configFilePath;
    }

    private static class Data {
        private final String basicFiltersComment = "Any console messages containing any of these strings will be hidden.";
        private final List<String> basicFilters;

        private final String regexFiltersComment = "Any console messages containing any of these regular expressions will be hidden. Uses Java regex syntax. Backslashes must be doubled, i.e. use \\s to match whitespace.";
        private final List<String> regexFilters;

        private final String loggerFiltersComment = "Any console messages from loggers with a name containing any of these strings will be hidden.";
        private final List<String> loggerFilters;

        private Data() {
            basicFilters = new ArrayList<>();
            regexFilters = new ArrayList<>();
            loggerFilters = new ArrayList<>();
        }

        private Data(List<String> basicFilters, List<String> regexFilters, List<String> loggerFilters) {
            this.basicFilters = basicFilters;
            this.regexFilters = regexFilters;
            this.loggerFilters = loggerFilters;
        }
    }

}
