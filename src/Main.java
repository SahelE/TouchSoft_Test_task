import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Main {

    public static void main(String args[]) {
        int[] nums = {4,1,6,1};
        Solution sol = new Solution();
        if (sol.canBeEqualTo24(nums)) System.out.println("true");
        else System.out.println("false");
    }
}
