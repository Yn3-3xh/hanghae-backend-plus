package org.example.junitinaction3.chapter06.printer.bad;

public class DocumentPrinter {

    private Document document;

    public DocumentPrinter(Document document) {
        this.document = document;
    }

    public void printDocument() {
        switch (document.getDocumentType()) {
            case WORD_DOCUMENT:
                printWORDDocument();
                break;
            case PDF_DOCUMENT:
                printPDFDocument();
                break;
            case TEXT_DOCUMENT:
                printTextDocument();
                break;
            default:
                printBinaryDocument();
                break;
        }
    }

    private void printBinaryDocument() {
    }

    private void printTextDocument() {
    }

    private void printPDFDocument() {
    }

    private void printWORDDocument() {
    }
}