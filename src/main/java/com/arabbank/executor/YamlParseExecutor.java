package com.arabbank.executor;

import com.arabbank.function.YamlParseFunction;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlParseExecutor implements YamlParseFunction {
    @Override
    public Map<String, Object> parse(String yamlName) {
        Yaml yaml = new Yaml();
        InputStream inputStream = YamlParseExecutor.class
                .getClassLoader()
                .getResourceAsStream(yamlName);
        return yaml.load(inputStream);
    }
}
