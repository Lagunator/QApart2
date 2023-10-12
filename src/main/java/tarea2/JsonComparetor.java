package tarea2;
import org.json.JSONObject;
public class JsonComparetor {
    public boolean compare(JSONObject expectedResult, JSONObject actualResult) {
        for (String key : expectedResult.keySet()) {
            if (!actualResult.has(key)) {
                printMismatch(key, "No hay resultada", expectedResult.get(key).toString());
                return false;
            }

            if (!"ignore".equals(expectedResult.get(key))) {
                if (!expectedResult.get(key).equals(actualResult.get(key))) {
                    printMismatch(key, actualResult.get(key).toString(), expectedResult.get(key).toString());
                    return false;
                }
            }
        }
        return true;
    }

    private void printMismatch(String key, String actualValue, String expectedValue) {
        System.out.println(String.format("%s ---> EXPECTED: %s ACTUAL: %s", key, expectedValue, actualValue));
    }
}
