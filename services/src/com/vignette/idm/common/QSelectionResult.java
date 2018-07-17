////////////////////////////////////////////////////////////////////////////////
//	Title		:	QSelectionResult.java
//
//	Description	:	Class which holds search results from queue selections and
//					number matched.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.common;

public class QSelectionResult
{
	public SelectionResult m_Results;
	public int m_NumMatched;
	public int m_Offset;
	
	public QSelectionResult()
	{
		m_Results = null;
		m_NumMatched = 0;
		m_Offset = 0;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
