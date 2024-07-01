/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Rogério
 */

public class FichaTreinoPDF {

    public static void gerarPdf(TreinoAplicacao treinoAplicacao, String caminhoArquivo) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivo));
            document.open();

            document.add(new Paragraph("ACADEMIA: " + treinoAplicacao.getAcademia().getNome()));
            document.add(new Paragraph("FICHA DE TREINO"));
            document.add(new Paragraph("Aluno(a): " + treinoAplicacao.getPessoa().getNome()));
            document.add(new Paragraph("DIVISÃO DE TREINO: " + treinoAplicacao.getDivTreino().getNome()));
            document.add(new Paragraph("INÍCIO: " + treinoAplicacao.getDataCriacao() + "  TÉRMINO: " + treinoAplicacao.getDataModificacao() + " - 6 SEMANAS"));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);

            addTableHeader(table, treinoAplicacao.divTreino.getNome(), "");
            addExerciseRow(table, "supino reto", "4x12");
            addExerciseRow(table, "desenvolvimento militar", "3x30");
            addExerciseRow(table, "tríceps pulley corda", "5x20");

            addTableHeader(table, "COSTAS, BÍCEPS", "");
            addExerciseRow(table, "barra fixa", "4x12");
            addExerciseRow(table, "rosca direta", "3x15");

            addTableHeader(table, "PERNA", "");
            addExerciseRow(table, "elevação pélvica", "3x12");
            addExerciseRow(table, "glúteo 4 apoios", "4x15");

            document.add(table);

            document.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTableHeader(PdfPTable table, String header1, String header2) {
        PdfPCell headerCell1 = new PdfPCell(new Phrase(header1));
        headerCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell1);

        PdfPCell headerCell2 = new PdfPCell(new Phrase(header2));
        headerCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell2);
    }

    private static void addExerciseRow(PdfPTable table, String exercise, String reps) {
        table.addCell(exercise);
        table.addCell(reps);
    }

}
