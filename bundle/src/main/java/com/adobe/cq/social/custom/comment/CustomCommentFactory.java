package com.adobe.cq.social.custom.comment;

import javax.jcr.RepositoryException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import com.adobe.cq.social.commons.client.api.ClientUtilities;
import com.adobe.cq.social.commons.client.api.QueryRequestInfo;
import com.adobe.cq.social.commons.client.api.SocialComponent;
import com.adobe.cq.social.commons.client.api.SocialComponentFactory;
import com.adobe.cq.social.commons.comments.api.Comment;
import com.adobe.cq.social.commons.comments.api.CommentSocialComponentFactory;
import com.adobe.cq.social.commons.comments.listing.CommentSocialComponentListProviderManager;

/**
 * CustomCommentFactory extends the default CommentSocialComponentFactory to leverage the default comment social
 * component implementation. This makes it possible to only make changes needed for customization without having to
 * implement all the APIs specified by {@link Comment}.
 */
@Component(name = "Custom Comment Social Component Factory")
@Service
public class CustomCommentFactory extends CommentSocialComponentFactory implements SocialComponentFactory {

	@Reference
	private CommentSocialComponentListProviderManager commentListProviderManager;

	@Override
	public SocialComponent getSocialComponent(final Resource resource) {
		try {
			return new CustomComment(resource, this.getClientUtilities(resource.getResourceResolver()),commentListProviderManager);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public SocialComponent getSocialComponent(final Resource resource, final SlingHttpServletRequest request) {
		try {
			return new CustomComment(resource, this.getClientUtilities(request),this.getQueryRequestInfo(request),commentListProviderManager);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public SocialComponent getSocialComponent(Resource resource, ClientUtilities clientUtils, QueryRequestInfo listInfo) {
		try {
			return new CustomComment(resource, clientUtils, listInfo,commentListProviderManager);
		} catch (RepositoryException e) {
			return null;
		}
	}


	/*
* (non-Javadoc)
* @see com.adobe.cq.social.commons.client.api.AbstractSocialComponentFactory#getPriority() Set the priority to a
* number greater than 0 to override the default SocialComponentFactory for comments.
*/
	@Override
	public int getPriority() {
		return 10;
	}

}