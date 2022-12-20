package by.bsuir.reader;

public class TextReader extends ReaderDecorator {

    public TextReader(Readable reader) {
        super(reader);
    }

    @Override
    public String readFile() {
        System.out.println("Decorating reading");
        return reader.readFile();
    }
}
