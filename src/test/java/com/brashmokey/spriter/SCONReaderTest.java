package com.brashmokey.spriter;

import static org.junit.Assert.*;

import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.SCMLReader;
import com.brashmonkey.spriter.SCONReader;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

public class SCONReaderTest {

    @Test
    public void should_load_scon_data_from_json_string () throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/test.scon")));
        int c = 0;
        while ((c = reader.read()) != -1) {
            textBuilder.append((char) c);
        }
        reader.close();

        SCONReader sconReader = new SCONReader(textBuilder.toString());
        Data data = sconReader.getData();

        assertTrue(data.scmlVersion.equals("1.0"));
        assertTrue(data.getEntity(0).name.equals("entity_000"));
    }

    @Test
    public void should_load_scon_data_from_input_stream () throws IOException {
        SCONReader sconReader = new SCONReader(new FileInputStream(new File("src/test/resources/test.scon")));
        Data data = sconReader.getData();

        assertTrue(data.scmlVersion.equals("1.0"));
        assertTrue(data.getEntity(0).name.equals("entity_000"));
    }

    @Test
    @Ignore
    public void should_be_as_fast_as_scml_reader () throws IOException {
        long sconBegin = System.nanoTime();
            SCONReader sconReader = new SCONReader(new FileInputStream(new File("src/test/resources/Roy/roy.scon")));
            Data sconData = sconReader.getData();
        long sconEnd = System.nanoTime();

        long scmlBegin = System.nanoTime();
            SCMLReader scmlReader = new SCMLReader(new FileInputStream(new File("src/test/resources/Roy/roy.scml")));
            Data scmlData = scmlReader.getData();
        long scmlEnd = System.nanoTime();

        long sconTime = sconEnd - sconBegin;
        long scmlTime = scmlEnd - scmlBegin;

        assertTrue(sconTime <= scmlTime);
    }
}
