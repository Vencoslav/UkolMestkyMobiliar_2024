import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainForm extends JFrame {
    private JPanel panelMain;
    private JButton btDalsi;
    private JButton btPredchozi;
    private JTextField txtPopis;
    private JCheckBox checkBox1;
    private JTextField txtDatum;
    private JTextField txtCena;
    private JRadioButton rbtLavicka;
    private JRadioButton rbtFitness;
    private JRadioButton rbtJine;
    private File selectedFile;
    private List<Mobiliar> seznam = new ArrayList<>();
    private int index = 0;

    public MainForm(){
        setSize(500,300);
        setContentPane(panelMain);
        setTitle("Městký mobiliář");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();

        btDalsi.addActionListener(e->{tlacitkoDalsi();});
        btPredchozi.addActionListener(e ->{tlacitkoPrechozi();});



    }
    public void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu soubor = new JMenu("Soubor");
        menuBar.add(soubor);

        JMenuItem nacti = new JMenuItem("Nacti");
        soubor.add(nacti);
        nacti.addActionListener(e -> {
            vyberSoubor();
        });
    }

    public void vyberSoubor(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("textové soubory", "txt"));
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            System.out.println("Vybrán soubor: " + selectedFile.getAbsolutePath()); // Debug výpis cesty k vybranému souboru
            nactiSoubor(selectedFile);
        }
    }


    public void nactiSoubor(File selectedFile) {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))) {
            while (sc.hasNextLine()) {
                String radek = sc.nextLine();
                String[] rozdelovac = radek.split("\t");
                if (rozdelovac.length != 5) {
                    throw new RuntimeException("Nesprávný počet prvků na řádku.");
                }
                String popis = rozdelovac[0].trim();
                Boolean urcenKVymene = Boolean.parseBoolean(rozdelovac[1].trim());
                LocalDate datumInstalace = LocalDate.parse(rozdelovac[2].trim());
                Double porizovaciCena = Double.parseDouble(rozdelovac[3].trim());
                String typ = rozdelovac[4].trim();
                Mobiliar mobiliar = new Mobiliar(popis, urcenKVymene, datumInstalace, porizovaciCena, typ);
                seznam.add(mobiliar);
                dislply();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Soubor:" + selectedFile + " nebyl nalezen. " + e.getLocalizedMessage());
        }
    }

    public void tlacitkoDalsi(){
        if(index < seznam.size()-1){
            index++;
            dislply();
        }
    }

    public void tlacitkoPrechozi(){
        if(index > 0){
            index--;
            dislply();
        }
    }

    public void dislply(){
        Mobiliar aktualniMobiliar = seznam.get(index);
        txtPopis.setText(aktualniMobiliar.getPopis());
        checkBox1.setSelected(aktualniMobiliar.getUrcenKVymene());
        txtDatum.setText(aktualniMobiliar.getDatumInstalace().toString());
        txtCena.setText(Double.toString(aktualniMobiliar.getPorizovaciCena()));
        rbtLavicka.setSelected(aktualniMobiliar.getTyp().equals("Lavička"));
        rbtFitness.setSelected(aktualniMobiliar.getTyp().equals("Fitness"));
        rbtJine.setSelected(aktualniMobiliar.getTyp().equals("Jiné"));

    }

}
