package com.adobe.cq.social.custom.comment;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;

import com.adobe.cq.social.commons.client.api.BaseSocialComponent;
import com.adobe.cq.social.commons.client.api.ClientUtilities;
import com.adobe.cq.social.commons.client.api.CollectionPagination;
import com.adobe.cq.social.commons.client.api.CollectionSortedOrder;
import com.adobe.cq.social.commons.client.api.QueryRequestInfo;
import com.adobe.cq.social.commons.client.api.ResourceID;
import com.adobe.cq.social.commons.client.api.SocialComponent;
import com.adobe.cq.social.commons.client.api.User;
import com.adobe.cq.social.commons.comments.api.AbstractComment;
import com.adobe.cq.social.commons.comments.api.Comment;
import com.adobe.cq.social.commons.comments.api.CommentCollectionConfiguration;
import com.adobe.cq.social.commons.comments.listing.CommentSocialComponentListProviderManager;
import com.adobe.cq.social.commons.moderation.impl.FlagReason;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomComment extends AbstractComment<CommentCollectionConfiguration> implements Comment<CommentCollectionConfiguration> {


	/**
	 * Construct a comment for the specified resource and client utilities.
	 * @param resource the specified resource
	 * @param clientUtils the client utilities instance
	 * @param commentListProviderManager list manager to use for listing content
	 * @throws RepositoryException if an error occurs
	 */
	public CustomComment(final Resource resource, final ClientUtilities clientUtils,
	                     final CommentSocialComponentListProviderManager commentListProviderManager) throws RepositoryException{
		super(resource, clientUtils, commentListProviderManager);
	}

	/**
	 * Constructor of a comment.
	 * @param resource the specified {@link com.adobe.cq.social.commons.Comment}
	 * @param clientUtils the client utilities instance
	 * @param queryInfo the query info.
	 * @param commentListProviderManager list manager to use for listing content
	 * @throws RepositoryException if an error occurs
	 */
	public CustomComment(final Resource resource, final ClientUtilities clientUtils,
	                     final QueryRequestInfo queryInfo, final CommentSocialComponentListProviderManager commentListProviderManager)
			throws RepositoryException {
		super(resource, clientUtils, queryInfo, commentListProviderManager);
	}

	/* (non-Javadoc)
* Example on how you can remove data from the HTTP API or Handlebars template context for a Comment.
* Simply return a null or empty if you wish to hide a piece of data.
* @see com.adobe.cq.social.commons.comments.api.Comment#getAuthor()
*/
	@Override
	@JsonIgnore
	public User getAuthor() {
		return null;
	}

	/**
	 * This is an example of how you can add data to the HTTP API and Handlebars templates context for a Comment.
	 * @return returns the authorID of the user who created this comment.
	 */
	public String getAuthorId() {
		return this.getAuthor().getUserId();
	}

}