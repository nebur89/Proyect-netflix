package com.everis.d4i.tutorial.utils.constants;

public class ExceptionConstants {

	public static final String ERROR = "ERROR";

	public static final String MESSAGE_INEXISTENT_SEASON = "SEASON INEXISTENT - Season does not exist";
	public static final String MESSAGE_INEXISTENT_CHAPTER = "CHAPTER INEXISTENT - Chapter does not exist";

	public static final String MESSAGE_INEXISTENT_CATEGORY = "CATEGORY INEXISTENT - Category does not exist";

	public static final String MESSAGE_INEXISTENT_ACTOR = "ACTOR INEXISTENT - Actor does not exist";

	public static final String MESSAGE_INEXISTENT_TVSHOW = " TV SHOW INEXISTENT - TvShow does not exist";

	public static final String MESSAGE_ALREADY_EXIST_CATEGORY = " ALREADY EXIST CATEGORY- There is already a category with this name ";

	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR - An internal server error has ocurred";

	private ExceptionConstants() {
		throw new IllegalStateException("Utility Class");
	}

}
