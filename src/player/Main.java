package player;

import java.io.IOException;

/**
 * Main entry point of your application.
 */
public class Main {

	/**
	 * Plays the input file using Java MIDI API and displays
	 * header information to the standard output stream.
	 * 
	 * <p>Your code <b>should not</b> exit the application abnormally using
	 * System.exit()</p>
	 * 
	 * @param file the name of input abc file
	 * @throws IOException, UnrecoverableException 
	 */
	public static void play(String file) throws IOException {
		ABCPlayer.play(file);
	}
	
	public static void main(String[] args){
	    try {
            play("sample_abc/debussy_clairdelune.abc");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    System.out.println();
	}
}
