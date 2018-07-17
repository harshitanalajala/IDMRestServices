////////////////////////////////////////////////////////////////////////////////
//	Title		:	Annotation.java
//
//	Description	:	Class which retrieves/saves annotations.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.logging.Level;

import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.StringBufferS;

import TTANNOTATIONS.ANNOT_Annotation_t;
import TTANNOTATIONS.ANNOT_Base_t;
import TTANNOTATIONS.ANNOT_Colour_t;
import TTANNOTATIONS.ANNOT_Ellipse_t;
import TTANNOTATIONS.ANNOT_FreeHand_t;
import TTANNOTATIONS.ANNOT_Highlight_t;
import TTANNOTATIONS.ANNOT_MarginComment_t;
import TTANNOTATIONS.ANNOT_Point_t;
import TTANNOTATIONS.ANNOT_StickyNote_t;
import TTANNOTATIONS.ANNOT_Subpage_t;
import TTANNOTATIONS.ANNOT_Underline_t;
import TTANNOTATIONS.AnnotPermission;
import TTANNOTATIONS.AnnotationException;
import TTANNOTATIONS.OBJECT_ID;
import TTANNOTATIONS.POA_NAME;
import TTANNOTATIONS.PageAnnotations;
import TTANNOTATIONS.PageAnnotationsHolder;
import TTANNOTATIONS.PenStyle;
import TTANNOTATIONS.SpecialType;
import TTANNOTATIONS.StatusType;
import TTANNOTATIONS.StructType;
import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.BadDocIdException;
import TTEXCEPTIONS.DocPermException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.InvalidDSException;
import TTEXCEPTIONS.TTlibException;
import TTEXCEPTIONS.UnknownDocException;
import TTEXCEPTIONS.UnknownPageException;

public class Annotation
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";
	private static final String PREFIX = "ANNOTINFO=";
	private static final String VERSION = "VERSION:1";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.Annotation"
		);

	private IdmRepImpl mRepository;

	/**
	 *	Initialises the newly created annotation object.
	 *
	 *	@param aRepository contains the ticket required to access the repository
	 *
	 */
	public Annotation(IdmRepImpl aRepository)
	{
		mRepository = aRepository;
	}

	/**
	 *	Fetches annotations for the specified document subpage if any.
	 *
	 *	@param anIfn identifies document
	 *	@param aPageNum identifies the specific page in document
	 *	@param aSubPageNum identifies the specific subpage of document page
	 *
	 *	@return Annotation data in Applet Viewer format
	 *
	 *	@throws TTException
	 */
	public String getAnnotations
	(
		String	anIfn,
		int		aPageNum,
		int		aSubPageNum
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anIfn", anIfn, ", ");
				msg.append("aPageNum", aPageNum, ", ");
				msg.append("aSubPageNum", aSubPageNum, "");
				mLogger.info(msg.toString());
			}
		
			// Define formal arguments.
			Class [] formalArgs = new Class []
			{
				String.class,
				String.class,
				Integer.TYPE,
				String.class,
				PageAnnotationsHolder.class
			};
			
			// Define input/output variables.
			PageAnnotationsHolder pgAnH = new PageAnnotationsHolder();
			String subpages =
				Integer.toString(aSubPageNum) + "-" +
				Integer.toString(aSubPageNum);

			// Define actual arguments.
			Object[] actualArgs = new Object []
			{
				mRepository.getIdmTicket(),
				anIfn,
				new Integer(aPageNum),
				subpages,
				pgAnH
			};
			
			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTANNOTATIONS.AnnotHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getAnnotations",
				formalArgs,
				actualArgs
			);
			
			String annot = corba2string(pgAnH.value);
			
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("annot", annot, "");
				mLogger.info(msg.toString());
			}

			return annot;
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (BadDocIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_ID_ILLEGAL
			);
		}
		catch (InvalidDSException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_UNKNOWN
			);
		}
		catch (DocPermException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED
			);
		}
		catch (UnknownDocException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_UNKNOWN
			);
		}
		catch (UnknownPageException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.SECTION_UNKNOWN
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}

	/**
	 *	Saves the annotations for the specified document.
	 *
	 *	@param anIfn identifies the document
	 *	@param anAnnotInfo identifies the annotations in Applet Viewer format
	 *	@param aForceWrite indicates whether to force the save or not
	 *
	 *	@return Updated annotation data in Applet Viewer format
	 *
	 *	@throws TTException
	 *
	 */
	public String setAnnotations
	(
		String	anIfn,
		String	anAnnotInfo,
		boolean	aForceWrite
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anIfn", anIfn, ", ");
				msg.append("anAnnotInfo", anAnnotInfo, ", ");
				msg.append("aForceWrite", aForceWrite, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class [] formalArgs = new Class []
			{
				String.class,
				Boolean.TYPE,
				PageAnnotationsHolder.class
			};

			// Define input/output variables.
			PageAnnotationsHolder pgAnH = string2corba(anAnnotInfo);
			pgAnH.value.imageIfn = anIfn;
			pgAnH.value.userId = mRepository.getUserId();

			// Define actual arguments.
			Object[] actualArgs = new Object []
			{
				mRepository.getIdmTicket(),
				new Boolean(aForceWrite),
				pgAnH
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTANNOTATIONS.AnnotHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"setAnnotations",
				formalArgs,
				actualArgs
			);

			// Check if there are any conflicts during the save.
			if (pgAnH.value.conflictCount > 0)
			{
				throw new TTException
				(
					"Save conflicts detected",
					TTRepositoryErrors.ANNOTATION_SAVE_CONFLICT
				);
			}

			String annot = corba2string(pgAnH.value);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("annot", annot, "");
				mLogger.info(msg.toString());
			}

			return annot;
		}
		catch (AnnotationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.ANNOTATION_ACCESS_FAILURE
			);
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (BadDocIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_ID_ILLEGAL
			);
		}
		catch (InvalidDSException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_UNKNOWN
			);
		}
		catch (DocPermException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED
			);
		}
		catch (UnknownDocException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_UNKNOWN
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}

	/**
	 *	Converts the annotation data into a string.
	 *
	 *	@param aPgAn is the annotation data to be converted to a string
	 *
	 *	@return Result of the conversion
	 *
	 */
	private String corba2string(PageAnnotations aPgAn)
	{
		StringBuffer buf = new StringBuffer();
		String permission;
		if (aPgAn.userCanCreate)
		{
			permission = "1";
		}
		else
		{
			permission = "0";
		}

		if (aPgAn.subSeq.length > 0)
		{
			// Prefix and version info.
			buf.append
			(
				PREFIX +
				VERSION
			);

			// Version info. and number of subpages.
			buf.append
			(
				" " +
				"1" +
				" " +
				Integer.toString(aPgAn.imagePageNo) +
				" " +
				Integer.toString(aPgAn.subSeq.length)
			);

			// Create permission and file version.
			buf.append
			(
				" " +
				permission +
				" " +
				Integer.toString(aPgAn.fileVersion)
			);

			// Subpages.
			for (int i = 0; i < aPgAn.subSeq.length; i++)
			{
				// Subpage ID and number of annotations in subpage.
				ANNOT_Subpage_t subpage = aPgAn.subSeq[i];
				int annotCount = subpage.annotSeq.length;
				buf.append
				(
					" " +
					Integer.toString(subpage.subpageId) +
					" " +
					Integer.toString(annotCount)
				);

				// Subpage annotations.
				for (int j = 0; j < annotCount; j++)
				{
					// Data common to all annotation types.
					ANNOT_Annotation_t annot = subpage.annotSeq[j];
					int annotType = annot.annotation.discriminator();
					buf.append
					(
						" " +
						Integer.toString(annot.status.value()) +
						" " +
						Integer.toString(annot.access.value()) +
						" " +
						Integer.toString(annot.creatorUser.length()) +
						" " +
						annot.creatorUser +
						" " +
						Integer.toString(annot.creatorGroup.length()) +
						" " +
						annot.creatorGroup +
						" " +
						Integer.toString(annot.creationTimestamp) +
						" " +
						Integer.toString(annot.lastModifiedUser.length()) +
						" " +
						annot.lastModifiedUser +
						" " +
						Integer.toString(annot.lastModifiedGroup.length()) +
						" " +
						annot.lastModifiedGroup +
						" " +
						Integer.toString(annot.lastModifiedTimestamp) +
						" " +
						Integer.toString(annot.colour.red) +
						" " +
						Integer.toString(annot.colour.green) +
						" " +
						Integer.toString(annot.colour.blue) +
						" " +
						Integer.toString(annotType)
					);

					// Data specific to annotation types.
					switch (annotType)
					{
					case StructType._C_ANNOT_HIGHLIGHT:
						ANNOT_Highlight_t hl = annot.annotation.hl();
						buf.append
						(
							" " +
							Integer.toString(hl.x1) +
							" " +
							Integer.toString(hl.y1) +
							" " +
							Integer.toString(hl.x2) +
							" " +
							Integer.toString(hl.y2) +
							" " +
							Integer.toString(hl.borderColour.red) +
							" " +
							Integer.toString(hl.borderColour.green) +
							" " +
							Integer.toString(hl.borderColour.blue) +
							" " +
							Integer.toString(hl.transparency) +
							" " +
							Integer.toString(hl.penWidth) +
							" " +
							Integer.toString(hl.lineStyle.value())
						);
						break;

					case StructType._C_ANNOT_STICKYNOTE:
						ANNOT_StickyNote_t sn = annot.annotation.sn();
						buf.append
						(
							" " +
							Integer.toString(sn.x1) +
							" " +
							Integer.toString(sn.y1) +
							" " +
							Integer.toString(sn.x2) +
							" " +
							Integer.toString(sn.y2) +
							" " +
							Integer.toString(sn.rotation) +
							" " +
							Integer.toString(sn.shape) +
							" " +
							Integer.toString(getDataType(sn.dataType)) +
							" " +
							Integer.toString(sn.dataLength) +
							" " +
							sn.data
						);
						break;

					case StructType._C_ANNOT_UNDERLINE:
						ANNOT_Underline_t ul = annot.annotation.ul();
						buf.append
						(
							" " +
							Integer.toString(ul.x1) +
							" " +
							Integer.toString(ul.y1) +
							" " +
							Integer.toString(ul.x2) +
							" " +
							Integer.toString(ul.y2) +
							" " +
							Integer.toString(ul.penWidth) +
							" " +
							Integer.toString(ul.lineStyle.value())
						);
						break;

					case StructType._C_ANNOT_MARGINCOMMENT:
						ANNOT_MarginComment_t mc = annot.annotation.mc();
						buf.append
						(
							" " +
							Integer.toString(mc.x1) +
							" " +
							Integer.toString(mc.y1) +
							" " +
							Integer.toString(mc.x2) +
							" " +
							Integer.toString(mc.y2) +
							" " +
							Integer.toString(mc.rotation) +
							" " +
							Integer.toString(mc.fontSize) +
							" " +
							Integer.toString(mc.fontStyle) +
							" " +
							Integer.toString(mc.fontFamily.length()) +
							" " +
							mc.fontFamily +
							" " +
							Integer.toString(getDataType(mc.dataType)) +
							" " +
							Integer.toString(mc.dataLength) +
							" " +
							mc.data
						);
						break;

					case StructType._C_ANNOT_FREEHAND:
						ANNOT_FreeHand_t fh = annot.annotation.fh();
						int pointCount = fh.points.length;
						buf.append
						(
							" " +
							Integer.toString(fh.x) +
							" " +
							Integer.toString(fh.y) +
							" " +
							Integer.toString(fh.penWidth) +
							" " +
							Integer.toString(fh.lineStyle.value()) +
							" " +
							Integer.toString(getDataType(fh.dataType)) +
							" " +
							Integer.toString(pointCount)
						);
						for (int k = 0; k < pointCount; k++)
						{
							ANNOT_Point_t point = fh.points[k];
							buf.append
							(
								" " +
								Integer.toString(point.x) +
								" " +
								Integer.toString(point.y)
							);
						}
						break;

					case StructType._C_ANNOT_ELLIPSE:
						ANNOT_Ellipse_t el = annot.annotation.el();
						buf.append
						(
							" " +
							Integer.toString(el.x1) +
							" " +
							Integer.toString(el.y1) +
							" " +
							Integer.toString(el.x2) +
							" " +
							Integer.toString(el.y2) +
							" " +
							Integer.toString(el.borderColour.red) +
							" " +
							Integer.toString(el.borderColour.green) +
							" " +
							Integer.toString(el.borderColour.blue) +
							" " +
							Integer.toString(el.transparency) +
							" " +
							Integer.toString(el.penWidth) +
							" " +
							Integer.toString(el.lineStyle.value())
						);
						break;
					}
				}
			}
		}
		else
		{
			buf.append
			(
				PREFIX +
				VERSION +
				" " +
				permission
			);
		}

		return buf.toString();
	}

	/**
	 *	Converts annotation data from a string.
	 *
	 *	@param
	 *
	 *	@return Result of the conversion
	 *
	 *	@throw TTException
	 *
	 */
	private PageAnnotationsHolder string2corba(String anAnnotInfo)
	throws TTException
	{
		final int MIN_HEADER_LENGTH = 19;

		// Check if a string has been provided before proceeding.
		if (anAnnotInfo == null || anAnnotInfo.length() < MIN_HEADER_LENGTH)
		{
			String msg = "Invalid annotation string";
			mLogger.severe(msg);
			throw new TTException
			(
				msg,
				TTRepositoryErrors.ANNOTATION_ACCESS_FAILURE
			);
		}

		PageAnnotations pgAn = new PageAnnotations();
		int offset;

		try
		{
			// Check the annotation version.
			offset = anAnnotInfo.indexOf('=');
			String tmpStr = getSubString(anAnnotInfo, offset);
			offset += tmpStr.length() + 1;
			tmpStr.trim();
			if (!tmpStr.equalsIgnoreCase(VERSION))
			{
				String msg = "Incompatible versions";
				mLogger.severe(msg);
				throw new TTException
				(
					msg,
					TTRepositoryErrors.ANNOTATION_ACCESS_FAILURE
				);
			}

			// Validate the overall permission.
			tmpStr = getSubString(anAnnotInfo, offset);
			int overallPermission = Integer.parseInt(tmpStr);
			offset += tmpStr.length() + 1;

			// Get page number.
			tmpStr = getSubString(anAnnotInfo, offset);
			pgAn.imagePageNo = Integer.parseInt(tmpStr);
			offset += tmpStr.length() + 1;

			// Get subpage count.
			tmpStr = getSubString(anAnnotInfo, offset);
			int subpageCount = Integer.parseInt(tmpStr);
			offset += tmpStr.length() + 1;

			// Get user can create permission.
			tmpStr = getSubString(anAnnotInfo, offset);
			pgAn.userCanCreate = (Integer.parseInt(tmpStr) == 0 ? false : true);
			offset += tmpStr.length() + 1;

			// Get file version.
			tmpStr = getSubString(anAnnotInfo, offset);
			pgAn.fileVersion = Short.parseShort(tmpStr);
			offset += tmpStr.length() + 1;

			// Allocate resources for subpages.
			pgAn.subSeq = new ANNOT_Subpage_t[subpageCount];

			// Get data for subpages.
			for (int i = 0; i < subpageCount; i++)
			{
				// Allocate resources for subpage.
				ANNOT_Subpage_t subSeq = new ANNOT_Subpage_t();
				pgAn.subSeq[i] = subSeq;

				// Get subpage number.
				tmpStr = getSubString(anAnnotInfo, offset);
				subSeq.subpageId = Short.parseShort(tmpStr);
				offset += tmpStr.length() + 1;

				// Get number of annotations in subpage.
				tmpStr = getSubString(anAnnotInfo, offset);
				int annotCount = Integer.parseInt(tmpStr);
				offset += tmpStr.length() + 1;

				// Allocate resources for annotations in subpage.
				subSeq.annotSeq = new ANNOT_Annotation_t[annotCount];

				// Get data for annotations in subpage.
				for (int j = 0; j < annotCount; j++)
				{
					// Allocate resources for annotation.
					ANNOT_Annotation_t annotSeq = new ANNOT_Annotation_t();
					annotSeq.colour = new ANNOT_Colour_t();
					subSeq.annotSeq[j] = annotSeq;

					// Get status.
					tmpStr = getSubString(anAnnotInfo, offset);
					annotSeq.status =
						StatusType.from_int(Integer.parseInt(tmpStr));
					offset += tmpStr.length() + 1;

					// Get access permission.
					tmpStr = getSubString(anAnnotInfo, offset);
					annotSeq.access =
						AnnotPermission.from_int(Integer.parseInt(tmpStr));
					offset += tmpStr.length() + 1;

					// Get creator user.
					tmpStr = getSubString(anAnnotInfo, offset);
					int creatorUserLen = Integer.parseInt(tmpStr);
					offset += tmpStr.length() + 1;
					tmpStr = anAnnotInfo.substring
					(
						offset + 1,
						offset + 1 + creatorUserLen
					);
					annotSeq.creatorUser = tmpStr;
					offset += creatorUserLen + 1;

					// Get creator group.
					tmpStr = getSubString(anAnnotInfo, offset);
					int creatorGroupLen = Integer.parseInt(tmpStr);
					offset += tmpStr.length() + 1;
					tmpStr = anAnnotInfo.substring
					(
						offset + 1,
						offset + 1 + creatorGroupLen
					);
					annotSeq.creatorGroup = tmpStr;
					offset += creatorGroupLen + 1;

					// Get creation timestamp.
					tmpStr = getSubString(anAnnotInfo, offset);
					annotSeq.creationTimestamp = Integer.parseInt(tmpStr);
					offset += tmpStr.length() + 1;

					// Get last modified user.
					tmpStr = getSubString(anAnnotInfo, offset);
					int lastModifiedUserLen = Integer.parseInt(tmpStr);
					offset += tmpStr.length() + 1;
					tmpStr = anAnnotInfo.substring
					(
						offset + 1,
						offset + 1 + lastModifiedUserLen
					);
					annotSeq.lastModifiedUser = tmpStr;
					offset += lastModifiedUserLen + 1;

					// Get last modified group.
					tmpStr = getSubString(anAnnotInfo, offset);
					int lastModifiedGroupLen = Integer.parseInt(tmpStr);
					offset += tmpStr.length() + 1;
					tmpStr = anAnnotInfo.substring
					(
						offset + 1,
						offset + 1 + lastModifiedGroupLen
					);
					annotSeq.lastModifiedGroup = tmpStr;
					offset += lastModifiedGroupLen + 1;

					// Get last modified timestamp.
					tmpStr = getSubString(anAnnotInfo, offset);
					annotSeq.lastModifiedTimestamp = Integer.parseInt(tmpStr);
					offset += tmpStr.length() + 1;

					// Get annotation colour.
					tmpStr = getSubString(anAnnotInfo, offset);
					annotSeq.colour.red = Short.parseShort(tmpStr);
					offset += tmpStr.length() + 1;
					tmpStr = getSubString(anAnnotInfo, offset);
					annotSeq.colour.green = Short.parseShort(tmpStr);
					offset += tmpStr.length() + 1;
					tmpStr = getSubString(anAnnotInfo, offset);
					annotSeq.colour.blue = Short.parseShort(tmpStr);
					offset += tmpStr.length() + 1;

					// Get annotation type.
					tmpStr = getSubString(anAnnotInfo, offset);
					int annotType = Integer.parseInt(tmpStr);
					offset += tmpStr.length() + 1;

					// Allocate resources for base annotation.
					annotSeq.annotation = new ANNOT_Base_t();
					switch (annotType)
					{
					case StructType._C_ANNOT_HIGHLIGHT:

						// Allocate resources for highlight annotation.
						ANNOT_Highlight_t hl = new ANNOT_Highlight_t();
						hl.borderColour = new ANNOT_Colour_t();
						annotSeq.annotation.hl(hl);

						// Get first set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.x1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.y1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get second set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.x2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.y2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get border colour.
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.borderColour.red = Short.parseShort(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.borderColour.green = Short.parseShort(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.borderColour.blue = Short.parseShort(tmpStr);
						offset += tmpStr.length() + 1;

						// Get transparency.
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.transparency = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get pen width.
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.penWidth = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get line style.
						tmpStr = getSubString(anAnnotInfo, offset);
						hl.lineStyle =
							PenStyle.from_int(Integer.parseInt(tmpStr));
						offset += tmpStr.length() + 1;
						break;

					case StructType._C_ANNOT_STICKYNOTE:

						// Allocate resources for sticky note annotation.
						ANNOT_StickyNote_t sn = new ANNOT_StickyNote_t();
						annotSeq.annotation.sn(sn);

						// Get first set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						sn.x1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						sn.y1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get second set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						sn.x2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						sn.y2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get rotation.
						tmpStr = getSubString(anAnnotInfo, offset);
						sn.rotation = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get shape.
						tmpStr = getSubString(anAnnotInfo, offset);
						sn.shape = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get data type.
						tmpStr = getSubString(anAnnotInfo, offset);
						sn.dataType = getSpecialType(Integer.parseInt(tmpStr));
						offset += tmpStr.length() + 1;

						// Get data length.
						tmpStr = getSubString(anAnnotInfo, offset);
						sn.dataLength = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get data.
						tmpStr = anAnnotInfo.substring
						(
							offset + 1,
							offset + 1 + sn.dataLength
						);
						sn.data = tmpStr;
						offset += tmpStr.length() + 1;
						break;

					case StructType._C_ANNOT_UNDERLINE:

						// Allocate resources for underline annotation.
						ANNOT_Underline_t ul = new ANNOT_Underline_t();
						annotSeq.annotation.ul(ul);

						// Get first set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						ul.x1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						ul.y1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get second set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						ul.x2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						ul.y2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get pen width.
						tmpStr = getSubString(anAnnotInfo, offset);
						ul.penWidth = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get line style.
						tmpStr = getSubString(anAnnotInfo, offset);
						ul.lineStyle =
							PenStyle.from_int(Integer.parseInt(tmpStr));
						offset += tmpStr.length() + 1;
						break;

					case StructType._C_ANNOT_MARGINCOMMENT:

						// Allocate resources for margin comment annotation.
						ANNOT_MarginComment_t mc = new ANNOT_MarginComment_t();
						annotSeq.annotation.mc(mc);

						// Get first set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						mc.x1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						mc.y1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get second set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						mc.x2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						mc.y2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get rotation.
						tmpStr = getSubString(anAnnotInfo, offset);
						mc.rotation = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get font size.
						tmpStr = getSubString(anAnnotInfo, offset);
						mc.fontSize = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get font style.
						tmpStr = getSubString(anAnnotInfo, offset);
						mc.fontStyle = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get font family length.
						tmpStr = getSubString(anAnnotInfo, offset);
						int fontFamilyLen = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get font family.
						tmpStr = anAnnotInfo.substring
						(
							offset + 1,
							offset + 1 + fontFamilyLen
						);
						mc.fontFamily = tmpStr;
						offset += tmpStr.length() + 1;

						// Get data type.
						tmpStr = getSubString(anAnnotInfo, offset);
						mc.dataType = getSpecialType(Integer.parseInt(tmpStr));
						offset += tmpStr.length() + 1;

						// Get data length.
						tmpStr = getSubString(anAnnotInfo, offset);
						mc.dataLength = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get data.
						tmpStr = anAnnotInfo.substring
						(
							offset + 1,
							offset + 1 + mc.dataLength
						);
						mc.data = tmpStr;
						offset += tmpStr.length() + 1;
						break;

					case StructType._C_ANNOT_FREEHAND:

						// Allocate resources for freehand annotation.
						ANNOT_FreeHand_t fh = new ANNOT_FreeHand_t();
						annotSeq.annotation.fh(fh);

						// Get set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						fh.x = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						fh.y = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get pen width.
						tmpStr = getSubString(anAnnotInfo, offset);
						fh.penWidth = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get line style.
						tmpStr = getSubString(anAnnotInfo, offset);
						fh.lineStyle =
							PenStyle.from_int(Integer.parseInt(tmpStr));
						offset += tmpStr.length() + 1;

						// Get data type.
						tmpStr = getSubString(anAnnotInfo, offset);
						fh.dataType = getSpecialType(Integer.parseInt(tmpStr));
						offset += tmpStr.length() + 1;

						// Get number of points.
						tmpStr = getSubString(anAnnotInfo, offset);
						int pointCount = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Allocate resources for points.
						fh.points = new ANNOT_Point_t[pointCount];

						// Get data for points.
						for (int k = 0; k < pointCount; k++)
						{
							// Allocate resources for point.
							ANNOT_Point_t point = new ANNOT_Point_t();
							fh.points[k] = point;

							// Get point coordinates.
							tmpStr = getSubString(anAnnotInfo, offset);
							point.x = Integer.parseInt(tmpStr);
							offset += tmpStr.length() + 1;
							tmpStr = getSubString(anAnnotInfo, offset);
							point.y = Integer.parseInt(tmpStr);
							offset += tmpStr.length() + 1;
						}
						break;

					case StructType._C_ANNOT_ELLIPSE:

						// Allocate resources for ellipse annotation.
						ANNOT_Ellipse_t el = new ANNOT_Ellipse_t();
						el.borderColour = new ANNOT_Colour_t();
						annotSeq.annotation.el(el);

						// Get first set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						el.x1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						el.y1 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get second set of coordinates.
						tmpStr = getSubString(anAnnotInfo, offset);
						el.x2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						el.y2 = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get border colour.
						tmpStr = getSubString(anAnnotInfo, offset);
						el.borderColour.red = Short.parseShort(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						el.borderColour.green = Short.parseShort(tmpStr);
						offset += tmpStr.length() + 1;
						tmpStr = getSubString(anAnnotInfo, offset);
						el.borderColour.blue = Short.parseShort(tmpStr);
						offset += tmpStr.length() + 1;

						// Get transparency.
						tmpStr = getSubString(anAnnotInfo, offset);
						el.transparency = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get pen width.
						tmpStr = getSubString(anAnnotInfo, offset);
						el.penWidth = Integer.parseInt(tmpStr);
						offset += tmpStr.length() + 1;

						// Get line style.
						tmpStr = getSubString(anAnnotInfo, offset);
						el.lineStyle =
							PenStyle.from_int(Integer.parseInt(tmpStr));
						offset += tmpStr.length() + 1;
						break;
					}
				}
			}
		}
		catch (IndexOutOfBoundsException ioobe)
		{
			String msg = "Insufficient annotation data";
			mLogger.severe(msg);
			throw new TTException
			(
				msg,
				TTRepositoryErrors.ANNOTATION_ACCESS_FAILURE
			);
		}
		catch (NumberFormatException nfe)
		{
			String msg = "Annotation data representation error";
			mLogger.severe(msg);
			throw new TTException
			(
				msg,
				TTRepositoryErrors.ANNOTATION_ACCESS_FAILURE
			);
		}

		return new PageAnnotationsHolder(pgAn);
	}

	/**
	 *	Converts a special type to a data type.
	 *
	 *	@param aSt is the specified special type
	 *
	 *	@return The corresponding data type for the special type
	 *
	 */
	private int getDataType(SpecialType aSt)
	{
		return ((aSt.value() == 0) ? 1000 : 1001);
	}

	/**
	 *	Obtains the next substring starting from the specified position and
	 *	ending on the next space character.
	 *
	 *	@param aStr is string to take substring from
	 *	@param aStartIndex is starting position for substring
	 *
	 *	@return Resulting substring
	 *
	 */
	private String getSubString(String aStr, int aStartIndex)
	{
		int length = aStr.indexOf(" ", aStartIndex + 1);
		if (length == -1)
		{
			length = aStr.length();
		}
		return aStr.substring(aStartIndex + 1, length);
	}

	/**
	 *	Converts a data type to a special type.
	 *
	 *	@param aDt is the specified data type
	 *
	 *	@return The corresponding special type for the data type
	 *
	 */
	private SpecialType getSpecialType(int aDt)
	{
		return SpecialType.from_int(aDt == 1000 ? 0 : 1);
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
