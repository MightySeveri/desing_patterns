interface Printer {
    void print(String message);
}

class BasicPrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}

class XMLPrinter implements Printer {
    private Printer printer;

    public XMLPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        printer.print("<message>" + message + "</message>");
    }
}

class EncryptedPrinter implements Printer {
    private Printer printer;

    public EncryptedPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        String encrypted = "salaisuuksia horisontissa";
        printer.print(encrypted);
    }
}

class Main {
    public static void main(String[] args) {
        Printer printer = new BasicPrinter();
        printer.print("Hello World!");

        Printer printer2 =
            new EncryptedPrinter(
                new XMLPrinter(
                    new BasicPrinter()
                )
            );

        printer2.print("Hello World!");
    }
}
