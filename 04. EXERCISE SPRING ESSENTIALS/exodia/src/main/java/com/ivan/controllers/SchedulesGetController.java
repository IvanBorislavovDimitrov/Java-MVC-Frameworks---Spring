package com.ivan.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.ivan.constants.ViewConstants;
import com.ivan.models.view.DocumentViewModel;
import com.ivan.services.api.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SchedulesGetController extends BaseController {

    private static final String DOCUMENTS = "documents";
    private static final String NEEDED_BOXES = "neededBoxes";
    private static final String DOCUMENT = "document";

    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    public SchedulesGetController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/home")
    public ModelAndView home(Model model) {
        List<DocumentViewModel> documentViewModels = getAll();

        model.addAttribute(NEEDED_BOXES, getNeededBoxes(documentViewModels));
        model.addAttribute(DOCUMENTS, documentViewModels);

        return renderView(ViewConstants.VIEWS_HOME, model);
    }

    @GetMapping("/details")
    public ModelAndView schedule(@RequestParam("id") String id, Model model) {
        model.addAttribute(DOCUMENT, modelMapper.map(documentService.getById(id), DocumentViewModel.class));

        return renderView(ViewConstants.VIEWS_DETAILS, model);
    }

    @GetMapping("/print")
    public ModelAndView print(@RequestParam("id") String id, Model model) {
        model.addAttribute(DOCUMENT, modelMapper.map(documentService.getById(id), DocumentViewModel.class));

        return renderView(ViewConstants.VIEWS_PRINT, model);
    }

    @PostMapping("/print")
    public ModelAndView printConfirm(@RequestParam("id") String id) throws IOException {
        documentService.deleteById(id);

        return redirect(ViewConstants.REDIRECT_HOME);
    }

    @PostMapping("/download")
    public ResponseEntity<Resource> print(@RequestParam("id") String id) throws IOException {
        DocumentViewModel documentViewModel = modelMapper.map(documentService.getById(id), DocumentViewModel.class);
        String pdfContent = createPdfString(documentViewModel.getContent(), documentViewModel.getTitle());

        return download(documentViewModel.getTitle(), pdfContent);
    }

    private List<DocumentViewModel> getAll() {
        return documentService.getAll()
                .stream()
                .map(documentServiceModel -> {
                    DocumentViewModel documentViewModel = new DocumentViewModel();
                    documentViewModel.setTitle(documentServiceModel.getTitle().length() <= 12 ?
                            documentServiceModel.getTitle() : documentServiceModel.getTitle().substring(0, 12) + "...");
                    documentViewModel.setContent(documentServiceModel.getContent());
                    documentViewModel.setId(documentServiceModel.getId());

                    return documentViewModel;
                })
                .collect(Collectors.toList());
    }

    private List<Integer> getNeededBoxes(List<DocumentViewModel> documentViewModels) {
        int rows = documentViewModels.size() / 5 + 1;
        if (documentViewModels.size() > 0 && documentViewModels.size() % 5 == 0) {
            rows--;
        }
        int total = rows * 5;
        int neededBoxes = total - documentViewModels.size();

        return new ArrayList<>(Arrays.asList(new Integer[neededBoxes]));
    }

    private ResponseEntity<Resource> download(String title, String createdContent) throws IOException {
        getDocument(title, createdContent);

        String home = System.getProperty("user.dir");

        File file = new File(home + "/" + title + ".pdf");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("Refresh", "1; url = index")
                .header("Content-Disposition", "attachment;filename=" + title + ".pdf")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("multipart/form-data"))
                .body(resource);
    }

    private void getDocument(String title, String content) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            String k = String.format("<html><body>%s</body></html>", content);
            htmlWorker.parse(new StringReader(k));
            document.close();

            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private String createPdfString(String content, String title) {
        String[] lines = content.split(System.lineSeparator());
        StringBuilder sb = new StringBuilder();
        sb.append("<center>").append("<h1>").append(title).append("</h1>").append("</br>");
        for (String line : lines) {
            sb.append(enhanceRow(line)).append("</br>");
        }

        sb.append("</center>");
        return sb.toString();
    }

    private String enhanceRow(String row) {
        if (row.startsWith("#")) {
            int lastIndexDash = row.lastIndexOf("#");
            int dashesCount = dashesCount(row);

            return "<h1" + dashesCount + ">" + row.substring(lastIndexDash + 1) + "</h1" + dashesCount + ">";
        } else if (row.startsWith("**") && row.endsWith("**")) {
            return "<strong>" + row.substring(2, row.length() - 2) + "</strong>";
        } else if (row.startsWith("*")) {
            return "<ul>" + "<li>" + row.substring(1) + "</li>" + "</ul>";
        }

        return row;
    }

    private int dashesCount(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                cnt++;
            }
        }

        return cnt;
    }
}
