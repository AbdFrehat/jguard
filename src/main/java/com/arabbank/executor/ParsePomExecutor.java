package com.arabbank.executor;

import com.arabbank.model.PomFile;
import com.arabbank.process.ParsePomProcess;
import com.arabbank.provider.FilesProvider;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParsePomExecutor implements ParsePomProcess {
    private static final Logger logger = LoggerFactory.getLogger(ParsePomExecutor.class);
    private final FilesProvider fileProvider;
    public static PomFile pomFile;

    public ParsePomExecutor() {
        this.fileProvider = new FilesProvider();
    }

    @Override
    public void parse() {
        Map<String, String> tags = new HashMap<>();
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader(fileProvider.provide("pom.xml").get(0)));
            tags.put(model.getGroupId(), model.getGroupId());
            tags.put(model.getArtifactId(), model.getArtifactId());
            tags.put(model.getVersion(), model.getVersion());
        } catch (IOException | XmlPullParserException e) {
            logger.error("Error parsing file pom.xml");
        }
        pomFile = new PomFile(tags);
    }
}
