////////////////////////////////////////////////////////////////////////////////
//	Title		:	RenditionDetail.java
//
//	Description	:	Class which holds details of a rendition.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.common;

public class RenditionDetail
{
	private String renditionType;
	private String mimeType;
	private int zoomFactor;

 /**
  * This constructor takes all details of a rendition and caches them internally.
  */
	public RenditionDetail
	(
		String	aRenditionType,
		String	aMIMEType,
		int		aZoomFactor
	)
	{
		renditionType = aRenditionType;
		mimeType = aMIMEType;
		zoomFactor = aZoomFactor;
	}

 /**
  * Gets the type of the rendition.
  * @return The type of the rendition.
  */
	public String getRenditionType()
	{
		return renditionType;
	}

 /**
  * Gets the MIME type of the rendition.
  * @return The MIME type of the rendition.
  */
	public String getMIMEType()
	{
		return mimeType;
	}

 /**
  * Gets the zoom factor of the rendition.
  * @return The zoom factor of the rendition.
  */
	public int getZoomFactor()
	{
		return zoomFactor;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
