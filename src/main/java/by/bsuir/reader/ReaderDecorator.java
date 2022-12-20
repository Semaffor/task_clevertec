package by.bsuir.reader;

public abstract class ReaderDecorator implements Readable {

    protected Readable reader;

    public ReaderDecorator(Readable reader) {
        this.reader = reader;
    }

    public abstract String readFile();

    @Override
    public void setFileName(String path) {
        reader.setFileName(path);
    }
}
