package hello;

import com.jacob.activeX.*;
import com.jacob.com.*;

/**
 * Provide Java APIs to manipulate BlueZone display session by encapsulating
 * BlueZone Host Automation Object methods via JACOB(Java COM bridge).
 */
class BlueZoneHAOUtil {
	private ActiveXComponent bzhao;

	public BlueZoneHAOUtil() {
		bzhao = new ActiveXComponent("BZWhll.WhllObj");
	}

	/**
	 * Disconnects from the host system and closes the BlueZone Display session
	 * window.
	 * 
	 * @param SessionTypeVal
	 *            0 - Mainframe; 1 - iSeries; 2 - VT
	 * @param SessionIdentifierVal
	 *            1 for S1; 2 for S2; 3 for S3; etc.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's Guide"
	 */
	public int closeSession(int SessionTypeVal, int SessionIdentifierVal) {
		Variant retValue = Dispatch.call(bzhao, "CloseSession",
				new Object[] { new Variant(SessionTypeVal), new Variant(SessionIdentifierVal) });
		return retValue.getInt();
	}

	/**
	 * Opens a conversation with the BlueZone Display session. The Connect
	 * command must be called before any other BlueZone Host Automation object
	 * methods that access data in the host screen.
	 * <p>
	 * Connect auto-connects to the BlueZone session that launched the BlueZone
	 * Object or it searches for the first available session if launched from
	 * BlueZone desktop when no short name session identifier is specified.
	 * 
	 * @param sessionShortName
	 *            Uniquely identifies the BlueZone Display session. The session
	 *            name corresponds to the HLLAPI Short Name Session Identifier
	 *            configured in the Options -> API settings in the BlueZone
	 *            Display emulator.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int connect(String sessionShortName) {
		Variant retValue = Dispatch.call(bzhao, "Connect", sessionShortName);
		return retValue.getInt();
	}

	/**
	 * Opens a conversation with the BlueZone Display session. The connect()
	 * command must be called before any other BlueZone Host Automation object
	 * methods that access data in the host screen.
	 * <p>
	 * Connect auto-connects to the BlueZone session that launched the BlueZone
	 * Object or it searches for the first available session if launched from
	 * BlueZone desktop when no short name session identifier is specified.
	 * 
	 * @param sessionShortName
	 *            Uniquely identifies the BlueZone Display session. The session
	 *            name corresponds to the HLLAPI Short Name Session Identifier
	 *            configured in the Options -> API settings in the BlueZone
	 *            Display emulator.
	 * @param ConnectRetryTimeout
	 *            Used to set the time in seconds that connect() spends
	 *            attempting to connect to the BlueZone session. a zero (0) can
	 *            be used to cause the Connect to abort if the first attempt
	 *            fails.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int connect(String sessionShortName, int connectRetryTimeout) {
		Variant retValue = Dispatch.call(bzhao, "Connect",
				new Object[] { new Variant(sessionShortName), new Variant(connectRetryTimeout) });
		return retValue.getInt();
	}

	/**
	 * Retrieves the session's host connection status.
	 * 
	 * @return True if the session is connected to the host system, or False
	 *         otherwise.
	 */
	public boolean connected() {
		Variant retValue = Dispatch.call(bzhao, "Connected");
		return retValue.getBoolean();
	}

	// YRJTest: need test
	/**
	 * Executes the Edit -> Copy to Clipboard function.
	 */
	public void copy() {
		Dispatch.call(bzhao, "Copy");
	}

	// YRJTest: don't know what happens when param ScreenVal is not 32, like 0,
	// 1?
	/**
	 * Retrieves the session's host connection status.
	 * 
	 * @param ScreenVal
	 *            When set to 32 then the Edit -> Select All function is
	 *            executed before the Copy.
	 */
	public void copy(int ScreenVal) {
		Dispatch.call(bzhao, "Copy", ScreenVal);
	}

	/**
	 * Get the cursor's column position. This property is 0-Base for VT/6530
	 * sessions and 1-Base for all other session types.
	 * 
	 * @return The cursor's column position.
	 */
	public int getCursorColumn() {
		Variant retValue = Dispatch.call(bzhao, "CursorColumn");
		return retValue.getInt();
	}

	/**
	 * Set the cursor's column position. This property is 0-Base for VT/6530
	 * sessions and 1-Base for all other session types.
	 * @param colVal rowVal Specify the new column position.
	 */
	public void setCursorColumn(int colVal) { 
		Dispatch.call(bzhao, "CursorColumn", colVal);

	}

	/**
	 * Get the cursor's row position. This property is 0-Base for VT/6530
	 * sessions and 1-Base for all other session types.
	 * 
	 * @return The cursor's row position
	 */
	public int getCursorRow() {
		Variant retValue = Dispatch.call(bzhao, "CursorRow");
		return retValue.getInt();
	}

	/**
	 * Set the cursor's row position. This property is 0-Base for VT/6530
	 * sessions and 1-Base for all other session types.
	 * @param rowVal Specify the new row position.
	 */
	public void setCursorRow(int rowVal) { 
		Dispatch.call(bzhao, "CursorRow", rowVal);
	}

	/**
	 * Closes an emulation session.
	 * 
	 * @param sessionNameStr
	 *            String containing the short name session identifier of the
	 *            session to close.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int deleteSession(String sessionNameStr) {
		Variant retValue = Dispatch.call(bzhao, "DeleteSession", sessionNameStr);
		return retValue.getInt();
	}

	/**
	 * The disconnect() method is used in conjunction with the connect() method,
	 * to halt communication between the Host Automation Object and the
	 * currently connected BlueZone session. Disconnect must be called when a
	 * subroutine or module is done with the host session.
	 * <p>
	 * To reestablish communication with the same BlueZone Session or a new
	 * BlueZone session, use the connect() method. See the connect() method for
	 * more information.
	 * <p>
	 * The Disconnect method must be used in conjunction with the Connect method
	 * as shown in the following example.
	 * 
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int disconnect() {
		Variant retValue = Dispatch.call(bzhao, "Disconnect");
		return retValue.getInt();
	}

	/**
	 * Executes the File -> Exit function.
	 * <p>
	 * The exit() method can be used to close a BlueZone session.
	 */
	public void exit() {
		Dispatch.call(bzhao, "Exit");
	}

	/**
	 * Sets the end position of an edit selection.
	 * <p>
	 * If there is no edit selection when this method is called, then the edit
	 * selection start position is set to row 1, column 1.
	 * 
	 * @param rowVal
	 *            The end row of the edit selection.
	 * @param columnVal
	 *            The end column of the edit selection.
	 */

	public void extendSelectionRect(int rowVal, int columnVal) {
		Dispatch.call(bzhao, "ExtendSelectionRect", new Object[] { new Variant(rowVal), new Variant(columnVal) });

	}

	/*
	public void field() { 
		Dispatch.call(bzhao, "Field", new Object[] { new Variant(), new Variant() });
	}
	//*/

	/**
	 * Controls window and keyboard focus.
	 * 
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int focus() {
		Variant retValue = Dispatch.call(bzhao, "Focus");
		return retValue.getInt();
	}

	/**
	 * Copy the contents of the clipboard to a string variable.
	 * 
	 * @return The text retrieved from the clipboard.
	 */
	public String getClipboardText() {
		Variant retValue = Dispatch.call(bzhao, "GetClipboardText", new Object[] { new Variant(), new Variant() });
		return retValue.toString();
	}

	/*
	 * // YRJTest: need to test how this method works. Seems it set two values
	 * and // returns a return code. public int getCursor() { Variant retValue =
	 * Dispatch.call(bzhao, "GetCursor", new Object[] { new Variant(), new
	 * Variant() }); return retValue.getInt(); }
	 * 
	 * 
	 * // YRJTest necessary? public String getFolderName() { Variant retValue =
	 * Dispatch.call(bzhao, "GetFolderName", new Object[] { new Variant(), new
	 * Variant() }); return retValue.toString(); }
	 * 
	 * // YRJTest necessary? public String getOpenFilename() { Variant retValue
	 * = Dispatch.call(bzhao, "GetOpenFilename", new Object[] { new Variant(),
	 * new Variant() }); return retValue.toString(); }
	 * 
	 * // YRJTest necessary? public String getSaveAsFilename() { Variant
	 * retValue = Dispatch.call(bzhao, "GetSaveAsFilename", new Object[] { new
	 * Variant(), new Variant() }); return retValue.toString(); } //
	 */

	/**
	 * Returns the session identifier (1,2,3,etc.) of the currently connected
	 * session. This method can only be used after a successful connect() and
	 * can be used in subsequent calls that require a session identifier
	 * parameter.
	 * <p>
	 * <p>
	 * *Embedded BlueZone*
	 * <p>
	 * Although, while not necessary, this method can improve the performance of
	 * the script, because the BZHAO does not need to enumerate child windows of
	 * the browser when attempting to auto-locate the BlueZone sessions.
	 * 
	 * @return Returns the session identifier(1, 2, 3, etc.) of the currently
	 *         connected session.
	 */
	public int getSessionId() {
		Variant retValue = Dispatch.call(bzhao, "getSessionId");
		return retValue.getInt();
	}

	/**
	 * Returns the HLLAPI Short Name (''A'',''B'',''C'',etc.) of the currently
	 * connected BlueZone session. This method can only be used after a
	 * successful connect() and can be used in subsequent calls that require a
	 * session name parameter.
	 * <p>
	 * While not necessary, this method can improve the performance of the
	 * script, because the BZHAO does not need to enumerate child windows of the
	 * browser when attempting to auto-locate the BlueZone sessions. See
	 * getSessionId() for an example.
	 * 
	 * @return Returns the HLLAPI Short Name("A", "B", "C", etc.) of the
	 *         currently connected BlueZone session.
	 */
	public String getSessionName() {
		Variant sessionShortName = Dispatch.call(bzhao, "GetSessionName");
		return sessionShortName.toString();
	}

	/*
	 * 
	 * public int ifLogExists() { Variant retValue = Dispatch.call(bzhao,
	 * "IfLogExists", new Object[] { new Variant(), new Variant() }); return
	 * retValue.getInt(); }
	 * 
	 * 
	 * 
	 * public String inputBox(String promptStr, String defaultStr) { Variant
	 * retValue = Dispatch.call(bzhao, "InputBox", new Object[] { new
	 * Variant(promptStr), new Variant(defaultStr) }); return
	 * retValue.toString(); } //
	 */

	/**
	 * Used to lock or unlock the BlueZone session's keyboard.
	 * <p>
	 * If BlueZone is playing a script or macro, BlueZone auto-unlocks the
	 * keyboard when the script or macro completes. The LockKeyboard( False )
	 * method is only needed to unlock the BlueZone keyboard when a process
	 * external to BlueZone has completed.
	 * 
	 * @param lockVal
	 *            Setting to True locks the keyboard and setting it to False
	 *            unlocks the keyboard.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int lockKeyboard(boolean lockVal) {
		Variant retValue = Dispatch.call(bzhao, "LockKeyboard", lockVal);
		return retValue.getInt();
	}

	/*
	 * 
	 * // YRJTest: is this method necessary?
	 * 
	 * public int msgBox(String messageStr, int flagsVal) { Variant retValue =
	 * Dispatch.call(bzhao, "MsgBox", new Object[] { new Variant(messageStr),
	 * new Variant(flagsVal) }); return retValue.getInt(); }
	 * 
	 * // YRJTest: is this method necessary?
	 * 
	 * public int msgBox(String messageStr, int flagsVal, String title) {
	 * Variant retValue = Dispatch.call(bzhao, "MsgBox", new Object[] { new
	 * Variant(messageStr), new Variant(flagsVal), new Variant(title) }); return
	 * retValue.getInt(); } //
	 */

	/**
	 * Launches a new emulation session.
	 * 
	 * @param SessionTypeVal
	 *            1 – Mainframe; 2 – iSeries; 3 – VT
	 * @return String containing the session's short name session identifier to
	 *         be used with the Connect and/or DeleteSession methods.
	 */
	public String newSession(int SessionTypeVal) {
		Variant sessName = Dispatch.call(bzhao, "NewSession", SessionTypeVal);

		return sessName.toString();
	}

	// YRJTest: need to remove the sleep(2000) in such a original util BZHAO
	// method.
	// Thread.sleep(2000) can be added into the classes using this method.
	/**
	 * Launches a new emulation session.
	 * 
	 * @param SessionTypeVal
	 *            1 – Mainframe; 2 – iSeries; 3 – VT
	 * @param configFileStr
	 *            Name of the profile containing the session settings.
	 * @return String containing the session's short name session identifier to
	 *         be used with the Connect and/or DeleteSession methods.
	 */
	public String newSession(int SessionTypeVal, String configFileStr) {
		Variant sessName = Dispatch.call(bzhao, "NewSession",
				new Object[] { new Variant(SessionTypeVal), new Variant(configFileStr) });

		// try {
		// Thread.currentThread().sleep(2000);
		// } catch (Exception e) {
		// }
		return sessName.toString();
	}

	/**
	 * Launches a new emulation session.
	 * 
	 * @param SessionTypeVal
	 *            1 – Mainframe; 2 – iSeries; 3 – VT
	 * @param configFileStr
	 *            Name of the profile containing the session settings.
	 * @param LockKeyboardVal
	 *            If True, locks the user's keyboard until a call to
	 *            LockKeyboard( False ) is made.
	 * @return String containing the session's short name session identifier to
	 *         be used with the Connect and/or DeleteSession methods.
	 */
	public String newSession(int SessionTypeVal, String configFileStr, boolean LockKeyboardVal) {
		Variant sessName = Dispatch.call(bzhao, "NewSession",
				new Object[] { new Variant(SessionTypeVal), new Variant(configFileStr), new Variant(LockKeyboardVal) });
		return sessName.toString();
	}

	/**
	 * Starts a BlueZone Display session.
	 * <p>
	 * The OpenSession function waits for the specified number of screen paints
	 * to occur in the BlueZone session before continuing with script execution.
	 * This number is usually one (1) on Mainframe, VT, and 6530 systems and two
	 * (2) on iSeries systems. If the number of seconds specified in the
	 * TimeoutVal parameter elapses before the number of specified screen paints
	 * have occurred in the BlueZone session, then OpenSession returns with a
	 * non-zero error code. The CloseSession function can be used to end a
	 * session started with the OpenSession function.
	 * 
	 * @param sessionTypeVal
	 * @param sessionIdVal
	 * @param configFileStr
	 * @param TimeoutVal
	 * @param waitPaintsVal
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */

	public int openSession(int sessionTypeVal, int sessionIdVal, String configFileStr, int TimeoutVal,
			int waitPaintsVal) {
		Variant retValue = Dispatch.call(bzhao, "openSession",
				new Object[] { new Variant(sessionTypeVal), new Variant(sessionIdVal), new Variant(configFileStr),
						new Variant(TimeoutVal), new Variant(waitPaintsVal) });
		return retValue.getInt();
	}

	/**
	 * Execute the Edit -> Paste function.
	 */
	public void paste() {
		Dispatch.call(bzhao, "Paste");
	}

	/**
	 * Suspends script execution based on the value as noted below.
	 * 
	 * @param pauseTimeVal
	 *            Value to pause. If the value is less than 19, the pause time
	 *            is counted in seconds. If the value is greater than 19, the
	 *            pause time is counted in milliseconds.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int pause(int pauseTimeVal) {
		Variant retValue = Dispatch.call(bzhao, "Pause", pauseTimeVal);
		return retValue.getInt();
	}

	/**
	 * Executes the File -> Print Screen function.
	 * 
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int printScreen() {
		Variant retValue = Dispatch.call(bzhao, "PrintScreen");
		return retValue.getInt();
	}

	/**
	 * Executes the File -> Print Screen function.
	 * 
	 * @param headerStr
	 *            Page header.
	 * @param numCopiesVal
	 *            Number of copies to print.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int printScreen(String headerStr, int numCopiesVal) {
		Variant retValue = Dispatch.call(bzhao, "PrintScreen",
				new Object[] { new Variant(headerStr), new Variant(numCopiesVal) });
		return retValue.getInt();
	}

	// YRJTest
	/**
	 * Get the cursor position.
	 * <p>
	 * The cursor position starts at 1 in the upper-left corner of the window
	 * (row 1, column 1), and ends at the bottom-right of the window (max row
	 * times max column). For example, for a Model 2 - 24 x 80 screen, the last
	 * position is 1920.
	 * 
	 * @return The current cursor position.
	 */
	public int getPSCursorPos() {
		Variant retValue = Dispatch.call(bzhao, "PSCursorPos");
		return retValue.getInt();
	}

	/**
	 * Set the cursor position.
	 * <p>
	 * The cursor position starts at 1 in the upper-left corner of the window
	 * (row 1, column 1), and ends at the bottom-right of the window (max row
	 * times max column). For example, for a Model 2 - 24 x 80 screen, the last
	 * position is 1920.
	 * 
	 * @param posVal
	 *            Specify the new position.
	 */
	public void setPSCursorPos(int posVal) {
		Dispatch.call(bzhao, "PSCursorPos", posVal);
	}

	/**
	 * Reads text from the host screen into a variable.
	 * <p>
	 * The screen position starts at 1 in the upper-left corner of the window
	 * (row 1, column 1), and ends at the bottom-right of the window (max row
	 * times max column). For example, for a Model 2 - 24 x 80 screen, the last
	 * position is 1920.
	 * 
	 * @param lengthVal
	 *            The number of characters to read.
	 * @param posVal
	 *            The position in the host screen to start reading.
	 * @return String containing the text.
	 */
	public String psGetText(int lengthVal, int posVal) {
		Variant retValue = Dispatch.call(bzhao, "PSGetText",
				new Object[] { new Variant(lengthVal), new Variant(posVal) });
		return retValue.toString();
	}

	/**
	 * Performs a case-sensitive search for an occurrence of text in the host
	 * screen.
	 * <p>
	 * The screen position starts at 1 in the upper-left corner of the window
	 * (row 1, column 1), and ends at the bottom-right of the window (max row
	 * times max column). For example, for a Model 2 - 24 x 80 screen, the last
	 * position is 1920. The screen position starts at 1 in the upper-left
	 * corner of the window (row 1, column 1), and ends at the bottom-right of
	 * the window (max row times max column). For example, for a Model 2 - 24 x
	 * 80 screen, the last position is 1920.
	 * 
	 * @param searchStr
	 *            The text to search for.
	 * @param startPosVal
	 *            The position in the host screen to begin the search.
	 * @return The position in the host screen where the text was found, or 0 if
	 *         not found.
	 */
	public int psSearch(String searchStr, int startPosVal) {
		Variant retValue = Dispatch.call(bzhao, "PSSearch",
				new Object[] { new Variant(searchStr), new Variant(startPosVal) });
		return retValue.getInt();
	}

	/**
	 * Writes text to an unprotected area of the host screen.
	 * <p>
	 * The screen position starts at 1 in the upper-left corner of the window
	 * (row 1, column 1), and ends at the bottom-right of the window (max row
	 * times max column). For example, for a Model 2 - 24 x 80 screen, the last
	 * position is 1920.
	 * 
	 * @param textStr
	 * @param posVal
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int psSetText(String textStr, int posVal) {
		Variant retValue = Dispatch.call(bzhao, "PSSetText",
				new Object[] { new Variant(textStr), new Variant(posVal) });
		return retValue.getInt();
	}

	/**
	 * Returns the host field attribute value associated with the specified
	 * screen position.
	 * 
	 * @param posVal
	 *            The zero-based screen position to query.
	 *            <p>
	 *            The screen position starts at 0 in the upper-left corner of
	 *            the window (row 1, column 1), and ends at the bottom-right of
	 *            the window (max row times max column minus one). For example,
	 *            for a Model 2 - 24 x 80 screen, the last position is 1919.
	 * @return The host field attribute value in big-endian format:
	 *         <p>
	 *         Bit position -- Meaning <br>
	 *         0-1 -- Both set to 1 (field attribute byte) <br>
	 *         2 -- 0 = Unprotected data field ; 1 = Protected data field <br>
	 *         3 -- 0 = Alphanumeric data ; 1 = Numeric data only <br>
	 *         4-5 -- I/SPD: 00 = Normal intensity, pen not detectable ; 01 =
	 *         Normal intensity, pen detectable ; 10 = High intensity, pen
	 *         detectable ; 11 = Non-display, pen not detectable <br>
	 *         6 -- Reserved
	 */
	public int queryFieldAttribute(int posVal) {
		Variant retValue = Dispatch.call(bzhao, "QueryFieldAttribute", posVal);
		return retValue.getInt();
	}

	/**
	 * Close all running BlueZone sessions.
	 */
	public void quit() {
		Dispatch.call(bzhao, "Quit");

	}

	// YRJTest: don't know how to use this method
	/**
	 * Retrieves data from the host screen.
	 * <p>
	 * When the ReadScreen function returns, the bufferStr variable contains the
	 * host screen data.
	 * 
	 * @param bufferStr
	 *            Variable to contain host screen data.
	 * @param lengthVal
	 *            Number of characters to read.
	 * @param rowVal
	 *            Row position.
	 * @param colVal
	 *            Column position.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int readScreen(String bufferStr, int lengthVal, int rowVal, int colVal) {
		Variant retValue = Dispatch.call(bzhao, "ReadScreen", new Object[] { new Variant(bufferStr),
				new Variant(lengthVal), new Variant(rowVal), new Variant(colVal) });
		return retValue.getInt();
	}

	/*
	 * public int receiveFile(String receiveStr) { Variant retValue =
	 * Dispatch.call(bzhao, "ReceiveFile", receiveStr ); return
	 * retValue.getInt(); } //
	 */

	/**
	 * Executes a program.
	 * 
	 * @param commandStr
	 *            Name of program executable and any command line arguments to
	 *            run.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int run(String commandStr) {
		Variant retValue = Dispatch.call(bzhao, "Run", commandStr);
		return retValue.getInt();
	}

	/*
	 * public void runExternalMacro(String projectStr, String macroStr) {
	 * Dispatch.call(bzhao, "RunExternalMacro", new Object[] { new
	 * Variant(projectStr), new Variant(macroStr) });
	 * 
	 * }
	 * 
	 * public void runExternalMacro(String projectStr, String macroStr, String
	 * macroParmStr) { Dispatch.call(bzhao, "RunExternalMacro", new Object[] {
	 * new Variant(projectStr), new Variant(macroStr), new Variant(macroParmStr)
	 * });
	 * 
	 * }
	 * 
	 * public void runMacro(String macroStr) { Dispatch.call(bzhao, "RunMacro",
	 * macroStr);
	 * 
	 * }
	 * 
	 * public void runMacro(String macroStr, String macroParmStr) {
	 * Dispatch.call(bzhao, "RunMacro", new Object[] { new Variant(macroStr),
	 * new Variant(macroParmStr) });
	 * 
	 * }
	 * 
	 * public void runScript(String scriptStr) { Dispatch.call(bzhao,
	 * "RunScript", scriptStr);
	 * 
	 * }
	 * 
	 * //
	 */

	// YRJTest: this method sets two value and return a value.
	/**
	 * Searches the host screen for some specified text.
	 * <p>
	 * When the Search function returns, the RowVal and ColumnVal variables
	 * contain the position where the text was found. If the text was not found,
	 * or if the function returned an error, the RowVal and ColumnVal variables
	 * contain zero.
	 * 
	 * @param searchStr
	 *            Specifies the text to search for.
	 * @param rowVal
	 *            On input, the variable containing the row position where the
	 *            search is to begin. On output, this variable contains the row
	 *            position where the text was found.
	 * @param colVal
	 *            On input, the variable containing the column position where
	 *            the search is to begin. On output, this variable contains the
	 *            column position where the text was found.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int search(String searchStr, int rowVal, int colVal) {
		Variant retValue = Dispatch.call(bzhao, "Search",
				new Object[] { new Variant(searchStr), new Variant(rowVal), new Variant(colVal) });
		return retValue.getInt();
	}

	/**
	 * Initiates an IND$FILE file transfer upload to the mainframe host system.
	 * <p>
	 * Script execution is suspended until the file transfer is complete. The
	 * SendStr command parameters correspond to what you normally enter at the
	 * DOS prompt.
	 * 
	 * @param sendStr
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's Guide"
	 */
	public int sendFile(String sendStr) {
		Variant retValue = Dispatch.call(bzhao, "SendFile", sendStr);
		return retValue.getInt();
	}

	/**
	 * Sends a sequence of keys to the display session.
	 * <p>
	 * The SendKey function affects the host screen as if the user were typing
	 * on the keyboard. If a character is used in the code, then the case of the
	 * character is important.
	 * <p>
	 * Note: If you want to use the “at” sign (@) in the data string you must
	 * use the two-byte code “@@”.
	 * 
	 * @param writeStr
	 *            String of key codes. See the section "IBM 3270/5250 send keys"
	 *            in "Appendix A: Reference tables" in "Rocket BlueZone Advanced
	 *            Automation Developer's Guide" for a complete listing of valid
	 *            key codes and descriptions.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int sendKey(String writeStr) {
		Variant retValueOfSendKey = Dispatch.call(bzhao, "SendKey", new Object[] { new Variant(writeStr) });
		return retValueOfSendKey.getInt();
	}

	/*
	 * public setBrowserWnd( ) { Variant retValue = Dispatch.call(bzhao,
	 * "SetBrowserWnd", new Object[] { new Variant( ), new Variant( ) }); return
	 * retValue.getInt(); } //
	 */

	/**
	 * Sets the text contents of the clipboard from a string.
	 * <p>
	 * To clear the contents of the clipboard, use this method while passing an
	 * empty string.
	 * 
	 * @param textStr
	 */
	public void setClipboardText(String textStr) {
		Dispatch.call(bzhao, "SetClipboardText", textStr);
	}

	/**
	 * Sets the host screen cursor position.
	 * <p>
	 * BlueZone VT attempts to move the cursor on the screen by sending cursor
	 * movement commands to the host. Not all VT applications/screens support
	 * cursor movement commands.
	 * 
	 * @param rowVal
	 *            Row position.
	 * @param colVal
	 *            Column position.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int setCursor(int rowVal, int colVal) {
		Variant retValue = Dispatch.call(bzhao, "SetCursor", new Object[] { new Variant(rowVal), new Variant(colVal) });
		return retValue.getInt();
	}

	/**
	 * Controls the inter-process communications mechanism between the BlueZone
	 * Host Automation Object and the BlueZone display session.
	 * <p>
	 * By default the BHAO uses the BlueZone Whllapi.Dll module for
	 * inter-process communications. The BlueZone Whllapi.Dll uses the Windows
	 * Ddeml.Dll to initiate data transactions to the BlueZone session. Some
	 * applications can suspend thread execution and inadvertently inhibit the
	 * Windows Ddeml.Dll from performing data transactions. The SetDLLName
	 * function can be used to load the BlueZone Whlapi32.Dll module which uses
	 * file mapping (shared memory) for inter-process communications to the
	 * BlueZone display session.
	 * 
	 * @param nameStr
	 *            Name of the BlueZone API Dynamic Link Library for
	 *            inter-process communications.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int setDLLName(String nameStr) {
		Variant retValue = Dispatch.call(bzhao, "SetDLLName", nameStr);
		return retValue.getInt();
	}

	/**
	 * Allows the setting of the host TCP port. You must also set the BlueZone
	 * session type and session ID.
	 * 
	 * @param sessionType
	 *            0 - Mainframe; 1 - iSeries; 2 - VT ; 3 - UTS; 4 - T27; 6 -
	 *            6530
	 * @param sessionId
	 *            1 for S1; 2 for S2; 3 for S3, etc.
	 * @param tcpPort
	 *            TCP Port Number
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int setHostPort(int sessionType, int sessionId, int tcpPort) {
		Variant retValue = Dispatch.call(bzhao, "SetHostPort",
				new Object[] { new Variant(sessionType), new Variant(sessionId), new Variant(tcpPort) });
		return retValue.getInt();
	}

	/**
	 * Sets the start position of an edit selection.
	 * <p>
	 * A one character edit selection is created. The edit selection can be
	 * extended by calling the ExtendSelectionRect method.
	 * 
	 * @param rowVal
	 *            The start row of the edit selection.
	 * @param colVal
	 *            The start column of the edit selection.
	 */
	public void setSelectionStartPos(int rowVal, int colVal) {
		Dispatch.call(bzhao, "SetSelectionStartPos", new Object[] { new Variant(rowVal), new Variant(colVal) });

	}

	/**
	 * Starts a BlueZone COM trace. This command must be used in conjunction
	 * with StopTrace.
	 * <p>
	 * Used for troubleshooting purposes. It is best to place StartScript before
	 * you make the connection to the host so that you capture all important
	 * events from the beginning.
	 * 
	 * @param fileName
	 *            Name that you want to use for the trace file. The trace file
	 *            is written to the BlueZone Scripts folder not the Traces
	 *            folder. Also, if you prefer, you can specify the full path
	 *            location for the trace file.
	 */
	public void startTrace(String fileName) {
		Dispatch.call(bzhao, "StartTrace", new Object[] { new Variant(), new Variant() });

	}

	/**
	 * Returns the status of the host session.
	 * <p>
	 * While the Status method can return the current session status, the
	 * WaitReady method is the preferred way of waiting for a Ready session
	 * status after sending an AID-key to the host.
	 * <p>
	 * Note:<br>
	 * Not recommended for VT or 6530 sessions.
	 * 
	 * @return 0 - Ready<br>
	 *         4 - Presentation Space is busy<br>
	 *         5 - Keyboard is locked
	 */
	public int status() {
		Variant retValue = Dispatch.call(bzhao, "Status");
		return retValue.getInt();
	}

	/**
	 * Stops a BlueZone COM trace. Used in conjunction with StartTrace.
	 * <p>
	 * Used for troubleshooting purposes. Place StopTrace after you close the
	 * BlueZone session so that you capture as much information as possible.
	 */
	public void stopTrace() {
		Dispatch.call(bzhao, "StopTrace");

	}

	/**
	 * Returns a string representation of the specified value.
	 * <p>
	 * The str() function can be used to convert variable formats.
	 * 
	 * @param valueVal
	 *            Number to convert.
	 * @return An array of characters that represent the number.
	 */
	public String str(int valueVal) {
		Variant retValue = Dispatch.call(bzhao, "Str", valueVal);
		return retValue.toString();
	}

	/**
	 * Used before calling NewSession to set or override connection parameters.
	 * 
	 * @param hostAddrStr
	 *            Host address string used when establishing a connection to the
	 *            host system.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int tCPSetParameters(String hostAddrStr) {
		Variant retValue = Dispatch.call(bzhao, "TCPSetParameters", hostAddrStr);
		return retValue.getInt();
	}

	/**
	 * Used before calling NewSession to set or override connection parameters.
	 * 
	 * @param hostAddrStr
	 *            Host address string used when establishing a connection to the
	 *            host system.
	 * @param modelTypeVal
	 *            Model type value used when setting the default number of rows
	 *            and columns for the device to emulate: <br>
	 *            2 - 24x80 ; 3 - 32x80 ; 4 - 43x80 ; 5 - 27x132
	 * @param portVal
	 *            Port number used when establishing a connection to the host
	 *            system. This may be used instead of ModelTypeVal for VT and
	 *            6530 sessions.
	 * 
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int tCPSetParameters(String hostAddrStr, int modelTypeVal, int portVal) {
		Variant retValue = Dispatch.call(bzhao, "TCPSetParameters",
				new Object[] { new Variant(hostAddrStr), new Variant(modelTypeVal), new Variant(portVal) });
		return retValue.getInt();
	}

	/**
	 * A property to get or set the Telnet encryption type.
	 * 
	 * @return Returns the Telnet encryption type when using the RetVal
	 *         property. <br>
	 *         The supported values for Mainframe/iSeries are:<br>
	 *         0 = Off<br>
	 *         1 = Implicit SSL/TLS<br>
	 *         2 = Explicit SSL/TLS
	 *         <p>
	 *         The supported values for VT and 6530 are:<br>
	 *         0 = Off<br>
	 *         1 = SSL<br>
	 *         2 = TLS<br>
	 *         3 = SSH
	 */
	public int telnetEncryption() {
		Variant retValue = Dispatch.call(bzhao, "TelnetEncryption");
		return retValue.getInt();
	}

	/**
	 * Used to auto-type the password associated with a PasswordVault account
	 * name.
	 * <p>
	 * If the PasswordVault account name parameter is omitted, then BlueZone
	 * uses the account name associated with the current host screen. If
	 * PasswordVault is not enabled, or if the account name is not found, then
	 * this method prompts the user to enter a password to type.
	 * 
	 * @return 0 if the password was typed successfully, 2 if the prompt was
	 *         canceled, or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int typePassword() {
		Variant retValue = Dispatch.call(bzhao, "TypePassword");
		return retValue.getInt();
	}

	/**
	 * Used to auto-type the password associated with a PasswordVault account
	 * name.
	 * <p>
	 * If the PasswordVault account name parameter is omitted, then BlueZone
	 * uses the account name associated with the current host screen. If
	 * PasswordVault is not enabled, or if the account name is not found, then
	 * this method prompts the user to enter a password to type.
	 * 
	 * @param accountNameStr
	 * @return 0 if the password was typed successfully, 2 if the prompt was
	 *         canceled, or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int typePassword(String accountNameStr) {
		Variant retValue = Dispatch.call(bzhao, "TypePassword", accountNameStr);
		return retValue.getInt();
	}

	/**
	 * Auto-types the user name associated with a PasswordVault account name.
	 * <p>
	 * If the PasswordVault account name parameter is omitted, then BlueZone
	 * uses the account name associated with the current host screen. If
	 * PasswordVault is not enabled, or if the account name is not found, then
	 * this method prompts the user to enter a user name to type.
	 * 
	 * @return 0 if the password was typed successfully, 2 if the prompt was
	 *         canceled, or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int typeUserName() {
		Variant retValue = Dispatch.call(bzhao, "TypeUserName");
		return retValue.getInt();
	}

	/**
	 * Auto-types the user name associated with a PasswordVault account name.
	 * <p>
	 * If the PasswordVault account name parameter is omitted, then BlueZone
	 * uses the account name associated with the current host screen. If
	 * PasswordVault is not enabled, or if the account name is not found, then
	 * this method prompts the user to enter a user name to type.
	 * 
	 * @param accountNameStr
	 * @return 0 if the password was typed successfully, 2 if the prompt was
	 *         canceled, or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int typeUserName(String accountNameStr) {
		Variant retValue = Dispatch.call(bzhao, "TypeUserName", accountNameStr);
		return retValue.getInt();
	}

	/**
	 * Returns a number representation of the specified string.
	 * <p>
	 * The val() function can be used to convert variable formats.
	 * 
	 * @param stringStr
	 *            String of characters to convert.
	 * @return A number representing the specified string.
	 */
	public int val(String stringStr) {
		Variant retValue = Dispatch.call(bzhao, "Val", stringStr);
		return retValue.getInt();
	}

	/**
	 * Launches the BlueZone Session Manager application.
	 */
	public void viewStatus() {
		Dispatch.call(bzhao, "ViewStatus");

	}

	/**
	 * Suspends script execution for the time specified.
	 * 
	 * @param waitVal
	 *            The number of seconds to wait before continuing with the
	 *            script.
	 */
	public void waitScriptExecution(int waitVal) {
		Dispatch.call(bzhao, "Wait", waitVal);

	}

	/**
	 * Suspends script execution until the host screen is ready for keyboard
	 * input and the cursor is at the specified location.
	 * <p>
	 * The waitCursor() method can be used to verify that a specific host screen
	 * is being displayed before continuing with script execution.
	 * <p>
	 * If extraWaitVal is a value in the range of 1, 2, 3, … ,10 then script
	 * execution is suspended until the host machine sends the specified number
	 * of screen writes containing the keyboard restore/unlock command. (Not
	 * supported in VT or 6530.)
	 * <p>
	 * If extraWaitVal is greater than 10, script execution is suspended until
	 * the specified number of milliseconds have transpired.
	 * 
	 * @param timeoutVal
	 *            The number of seconds to wait before returning with a session
	 *            is busy error code.
	 * @param rowVal
	 *            Specifies the cursor row position in the host screen.
	 * @param colVal
	 *            Specifies the cursor column position is the host screen.
	 * @param extraWaitVal
	 *            The number of milliseconds to validate for a keyboard unlocked
	 *            status.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int waitCursor(int timeoutVal, int rowVal, int colVal, int extraWaitVal) {
		Variant retValue = Dispatch.call(bzhao, "WaitCursor", new Object[] { new Variant(timeoutVal),
				new Variant(rowVal), new Variant(colVal), new Variant(extraWaitVal) });
		return retValue.getInt();
	}

	/**
	 * Waits for the user to press keys while the focus is in the session
	 * window. The default is to wait forever.
	 * 
	 * @return An empty string if KeyStr was omitted, or if the timeout
	 *         occurred, or the keys pressed if a match was found.
	 */
	public String waitForKeys() {
		Variant retValue = Dispatch.call(bzhao, "WaitForKeys");
		return retValue.toString();
	}

	/**
	 * Waits for the user to press keys while the focus is in the session
	 * window.
	 * 
	 * @param timeoutVal
	 *            The number of seconds to wait. If omitted, the default is to
	 *            wait forever. The maximum finite wait time is 65535 seconds,
	 *            approximately 18 hours.
	 * @param keyStr
	 *            A string of characters representing the keys to wait for. If
	 *            omitted, the default is any key.
	 * @return An empty string if KeyStr was omitted, or if the timeout
	 *         occurred, or the keys pressed if a match was found.
	 */
	public String waitForKeys(int timeoutVal, String keyStr) {
		Variant retValue = Dispatch.call(bzhao, "WaitForKeys",
				new Object[] { new Variant(timeoutVal), new Variant(keyStr) });
		return retValue.toString();
	}

	/**
	 * Used after sending an AID key to wait for the host keyboard to unlock.
	 * (Not suggested for VT hosts as the hosts do not lock keyboards.)
	 * 
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int waitForReady() {
		Variant retValue = Dispatch.call(bzhao, "WaitForReady");
		return retValue.getInt();
	}

	/**
	 * Suspends script execution until the desired text is found in the host
	 * screen.
	 * <p>
	 * The waitForText() method can be used to verify that a specific host
	 * screen is being displayed before continuing with script execution.
	 * 
	 * @param textStr
	 *            The text string that you want to search for in the host
	 *            screen.
	 * @param rowVal
	 *            Specifies the start row position in the host screen where the
	 *            search is to begin.
	 * @param colVal
	 *            Specifies the start column position in the host screen where
	 *            the search is to begin.
	 * @param timeoutVal
	 *            The number of seconds to wait before returning with a session
	 *            is busy error code.
	 * 
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int waitForText(String textStr, int rowVal, int colVal, int timeoutVal) {
		Variant retValue = Dispatch.call(bzhao, "WaitForText", new Object[] { new Variant(textStr), new Variant(rowVal),
				new Variant(colVal), new Variant(timeoutVal) });
		return retValue.getInt();
	}

	/**
	 * Suspends script execution until the host screen is ready for keyboard
	 * input. (Not recommended for VT sessions.)
	 * <p>
	 * Remarks <br>
	 * The WaitReady function must be called each time after sending a attention
	 * identifier key (such as a PF key) to the display session. <br>
	 * If ExtraWaitVal is a value in the range of 1, 2, 3, on up to 50, then
	 * script execution is suspended until the host machine sends the specified
	 * number of keyboard restores. Refer to the BlueZone status bar to
	 * determine the keyboard restore count for a given screen. <br>
	 * If ExtraWaitVal is set to 51 or higher, the operation of the parameter
	 * changes to specify the number of milliseconds to wait after the keyboard
	 * lock has been detected prior to executing the next script command.<br>
	 * Note:<br>
	 * WaitReady only works after an AID key is sent to the host. If WaitReady
	 * is used after data is put in a field, but not sent to the host, then the
	 * wait count is never reached and the command times out (first parameter).
	 * When converting macros and replacing WaitHostQuiet with WaitReady, ensure
	 * the preceding command is Enter, PFKey, Attn, SysReq, or some other AID
	 * key that causes the host to write to the screen.
	 * <p>
	 * Non-IBM remark<br>
	 * TimeoutVal and ExtraWaitVal behave differently when scripting non-IBM
	 * hosts. That is because keyboard locked status is not supported on non-IBM
	 * hosts. When scripting on non-IBM hosts, set TimeoutVal to 0 and treat
	 * ExtraWaitVal as a pause before the scripts moves on to the next command.
	 * See Example 2 below.
	 * 
	 * @param timeoutVal
	 *            The number of seconds to wait before returning with a session
	 *            is busy error code.
	 * @param extraWaitVal
	 *            The number of milliseconds to validate for a keyboard unlocked
	 *            status.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int waitReady(int timeoutVal, int extraWaitVal) {
		Variant retValue = Dispatch.call(bzhao, "WaitReady",
				new Object[] { new Variant(timeoutVal), new Variant(extraWaitVal) });
		return retValue.getInt();
	}

	/*
	public void window() {
		Variant retValue = Dispatch.call(bzhao, "Window", new Object[] { new Variant(), new Variant() });
		return retValue.getInt();
	}

	public void windowHandle() {
		Dispatch.call(bzhao, "WindowHandle");

	}
	//*/

	/**
	 * Set the property of the session's window state.
	 * @param stateVal 0 - normal; 1 - minimize; 2 - maximize
	 */
	public void setWindowState(int stateVal) {
		Dispatch.call(bzhao, "WindowState", stateVal);

	}

	/**
	 * Get the property of the session's window state.
	 * @return The window's state: 0 - normal; 1 - minimize; 2 - maximize
	 */
	public int getWindowState() {
		Variant retValue = Dispatch.call(bzhao, "WindowState");
		return retValue.getInt();
	}

	/**
	 * Pastes specified text in the host screen.
	 * <p>
	 * The writeScreen method can only paste text in unprotected fields in the
	 * host screen.
	 * <p>
	 * In a BlueZone VT session, the writeStr parameter is only echoed to the VT
	 * client screen. The writeStr parameter is never sent to the host.
	 * 
	 * @param writeStr
	 *            Text to place in host screen.
	 * @param rowVal
	 *            Row position.
	 * @param columnVal
	 *            Column position.
	 * @return 0 for success; or a non-zero error code. Refer to Section "Error
	 *         codes" in "Rocket BlueZone Advanced Automation Developer's
	 *         Guide".
	 */
	public int writeScreen(String writeStr, int rowVal, int columnVal) {
		Variant retValueOfWriteScreen = Dispatch.call(bzhao, "WriteScreen",
				new Object[] { new Variant(writeStr), new Variant(rowVal), new Variant(columnVal) });
		return retValueOfWriteScreen.getInt();
	}

}
