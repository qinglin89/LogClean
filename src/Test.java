import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> ls = new ArrayList<String>();
        ls.add("a");
        ls.add("b");
        System.out.println(ls.contains("c"));
        int a = Integer.parseInt("2");
        System.out.println(a+1);
        System.out.println("a".equals("a"));
    }
}
