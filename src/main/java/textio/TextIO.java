package textio;

public interface TextIO {
	void put(String message);
	String get(String prompt);
	int getInt(String prompt, int min, int max);
}
