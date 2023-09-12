package com.arabbank.executor;

import com.arabbank.function.PomFileFunction;
import com.arabbank.model.PomFile;
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

public class PomFileExecutor implements PomFileFunction {
    private static final Logger logger = LoggerFactory.getLogger(PomFileExecutor.class);
    private final FilesProvider fileProvider;

    public PomFileExecutor() {
        this.fileProvider = new FilesProvider();
    }

    @Override
    public PomFile readPomFile() {
        Map<String, String> tags = new HashMap<>();
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader(fileProvider.provide("test1").get(1)));
            tags.put(model.getGroupId(), model.getGroupId());
            tags.put(model.getArtifactId(), model.getArtifactId());
            tags.put(model.getVersion(), model.getVersion());
        } catch (IOException | XmlPullParserException e) {
            logger.error("Error parsing file pom.xml");
        }
        return new PomFile(tags);
    }
}
