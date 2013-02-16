USE [ApacheLogs]
GO

/****** Object:  Table [dbo].[InfoLogTable]    Script Date: 11/20/2010 16:09:51 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[InfoLogTable](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[LogName] [nchar](20) NOT NULL,
	[LogPath] [nvarchar](max) NOT NULL,
	[RemoteHost] [nchar](20) NULL,
	[RemoteLogname] [nchar](20) NULL,
	[RemoteUser] [nchar](20) NULL,
	[Time] [datetime] NULL,
	[FirstLineRequest] [nvarchar](50) NULL,
	[Status] [int] NULL,
	[ResponseSize] [int] NULL,
	[RemoteIP] [nchar](20) NULL,
	[LocalIP] [nchar](20) NULL,
	[FileName] [nvarchar](max) NULL,
	[RequestProtocol] [int] NULL,
	[TimeToServeRequest] [timestamp] NULL,
	[KeepaliveRequestNr] [int] NULL,
	[RequestMethod] [nchar](10) NULL,
	[QueryString] [nvarchar](max) NULL,
	[URL] [nvarchar](max) NULL,
	[CanonicalServerName] [nvarchar](50) NULL,
	[ConnectionStatus] [nchar](10) NULL,
 CONSTRAINT [PK_InfoLogTable] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


