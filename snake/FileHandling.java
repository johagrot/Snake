package snake;

import java.io.File;
import java.util.List;

public interface FileHandling {

	void write(File file, List<Integer> message);
	List<Integer> read(File file);
	void deleteContent(File file);
}
