package com.nativegame.natyengine.util.storage.xml;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class XMLOpenHelper {

    private static XMLOpenHelper INSTANCE;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private XMLOpenHelper() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static XMLOpenHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XMLOpenHelper();
        }

        return INSTANCE;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public XMLResultSet open(Context context, InputStream inputStream, String tagName) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            XMLResultSet resultSet = new XMLResultSet();
            findTag(parser, resultSet, tagName);
            return resultSet;
        } finally {
            inputStream.close();
        }
    }

    private void findTag(XmlPullParser parser, XMLResultSet resultSet, String tagName) throws IOException, XmlPullParserException {
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(tagName)) {
                readTag(parser, resultSet);
            } else {
                skipTag(parser);
            }
        }
    }

    private void readTag(XmlPullParser parser, XMLResultSet resultSet) throws XmlPullParserException, IOException {
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            String text = parser.nextText();
            resultSet.setValue(name, text);
        }
    }

    private void skipTag(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
    //========================================================

}
