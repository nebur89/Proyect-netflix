package com.everis.d4i.tutorial.utils.constants;

public class RestConstants {

	public static final String APPLICATION_NAME = "/netflix";
	public static final String API_VERSION_1 = "/v1";

	public static final String SUCCESS = "Success";

	public static final String RESOURCE_CATEGORY = "/categories";


	public static final String RESOURCE_CATEGORY_ID = "/categories/{categoryId}";
	public static final String RESOURCE_TV_SHOW = "/tv-shows";
	public static final String RESOURCE_TV_SHOW_ID = "{tvShowId}";

	public static final String RESOURCE_TV_SHOW_AWARDS= "/awards/{tvShowId}";

	public static final String RESOURCE_TV_SHOW_RENAME= "{tvShowId}/name/{tvShowName}";

	public static final String RESOURCE_ACTOR = "/actor";

	public static final String RESOURCE_ACTOR_FILE = "/file/actor-id/{actorId}";

	public static final String RESOURCE_ACTOR_LIST = "/list";

	public static final String RESOURCE_ACTOR_ID = "/actor-id/{actorId}";
	public static final String RESOURCE_SEASON = "/tv-shows/{tvShowId}/seasons";

	public static final String RESOURCE_CHAPTER_NUMBER = "/tv-shows/{tvShowId}/seasons/{seasonNumber}/chapter/{chapterNumber}";

	public static final String RESOURCE_CHAPTER = "/chapters";

	public static final String RESOURCE_CHAPTER_LIST = "list/tv-shows/{tvShowId}/seasons/{seasonNumber}";
	public static final String RESOURCE_ID = "/{id}";
	public static final String RESOURCE_RENAME_CHAPTER = "/tv-shows/{tvShowId}/seasons/{seasonNumber}/chapter/{chapterNumber}/chapter-name/{newChapterName}";
	public static final String RESOURCE_NUMBER = "/{number}";

	public static final String DESCRIPTION_CHAPTER = "chapter";

	
	public static final String PARAMETER_CATEGORY = "categories";

	public static final String PARAMETER_TV_SHOW_NAME = "tvShowName";

	public static final String PARAMETER_CATEGORY_ID = "categoryId";

	public static final String PARAMETER_TV_SHOW_ID = "tvShowId";

	public static final String PARAMETER_SEASON_NUMBER = "seasonNumber";

	public static final String PARAMETER_ACTOR = "actor";
	public static final String PARAMETER_ACTOR_ID = "actorId";

	public static final String PARAMETER_CHAPTER_NUMBER = "chapterNumber";
	public static final String PARAMETER_CHAPTER_NAME = "chapterName";


	private RestConstants() {
		throw new IllegalStateException("Utility Class");
	}

}
