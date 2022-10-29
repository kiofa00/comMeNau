USE [master]
GO
/****** Object:  Database [cmn]    Script Date: 10/26/2022 5:24:33 PM ******/
CREATE DATABASE [cmn]
 CONTAINMENT = NONE
GO
ALTER DATABASE [cmn] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [cmn].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [cmn] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [cmn] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [cmn] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [cmn] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [cmn] SET ARITHABORT OFF 
GO
ALTER DATABASE [cmn] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [cmn] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [cmn] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [cmn] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [cmn] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [cmn] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [cmn] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [cmn] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [cmn] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [cmn] SET  ENABLE_BROKER 
GO
ALTER DATABASE [cmn] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [cmn] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [cmn] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [cmn] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [cmn] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [cmn] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [cmn] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [cmn] SET RECOVERY FULL 
GO
ALTER DATABASE [cmn] SET  MULTI_USER 
GO
ALTER DATABASE [cmn] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [cmn] SET DB_CHAINING OFF 
GO
ALTER DATABASE [cmn] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [cmn] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [cmn] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [cmn] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'cmn', N'ON'
GO
ALTER DATABASE [cmn] SET QUERY_STORE = OFF
GO
USE [cmn]
GO
/****** Object:  UserDefinedFunction [dbo].[SessionId]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[SessionId] (@id int) 
returns char(5) 
as 
begin 
return 'S' + right('0000' + convert(varchar(10), @id), 4) 
end
GO
/****** Object:  Table [dbo].[Account]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[AId] [int] IDENTITY(1,1) NOT NULL,
	[account] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[role] [tinyint] NULL,
	[Verify] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[AId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Building]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Building](
	[Bid] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[Bid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CaId] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
	[type] [smallint] NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CaId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Chef]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Chef](
	[ChId] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](100) NULL,
	[salary] [float] NULL,
	[status] [bit] NULL,
	[address] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ChId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CuId] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
	[phone] [nvarchar](50) NULL,
	[address] [int] NULL,
	[email] [nvarchar](100) NULL,
	[AccountId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[CuId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Food]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Food](
	[FId] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[nutrition] [nvarchar](200) NULL,
	[categoryId] [int] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[FId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FoodItem]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FoodItem](
	[foodId] [int] NOT NULL,
	[mealId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[foodId] ASC,
	[mealId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FoodStyle]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FoodStyle](
	[FsId] [int] IDENTITY(1,1) NOT NULL,
	[type] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[FsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Image]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image](
	[IId] [int] IDENTITY(1,1) NOT NULL,
	[url] [nvarchar](200) NULL,
	[mealId] [int] NULL,
	[foodId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Like]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Like](
	[customerId] [int] NOT NULL,
	[chefId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[customerId] ASC,
	[chefId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MealGroup]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MealGroup](
	[MId] [int] IDENTITY(1,1) NOT NULL,
	[price] [float] NULL,
	[chefId] [int] NULL,
	[name] [nvarchar](50) NULL,
	[soldNumber] [int] NULL,
	[detail] [nvarchar](400) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MealGroupSession]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MealGroupSession](
	[mealId] [int] NOT NULL,
	[sessionId] [int] NOT NULL,
	[price] [float] NULL,
	[note] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[mealId] ASC,
	[sessionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OId] [int] IDENTITY(1,1) NOT NULL,
	[address] [int] NULL,
	[orderDetail] [nvarchar](50) NULL,
	[mealId] [int] NULL,
	[customerId] [int] NULL,
	[price] [float] NULL,
	[status] [int] NULL,
	[sessionId] [int] NULL,
	[PId] [int] NULL,
	[customerName] [nvarchar](100) NULL,
	[phone] [nvarchar](20) NULL,
	[email] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[OId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payment]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[PId] [int] IDENTITY(1,1) NOT NULL,
	[type] [nvarchar](100) NULL,
	[detail] [nvarchar](300) NULL,
PRIMARY KEY CLUSTERED 
(
	[PId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Session]    Script Date: 10/26/2022 5:24:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Session](
	[SId] [int] IDENTITY(1,1) NOT NULL,
	[timeline] [nvarchar](50) NOT NULL,
	[day] [nvarchar](50) NULL,
	[Code]  AS ([dbo].[SessionId]([SId])),
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[SId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (1, N'admin', N'123456', 1, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (2, N'user1', N'123456', 2, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (3, N'user2', N'123456', 3, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (4, N'user3', N'123456', 2, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (5, N'user4', N'123456', 2, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (6, N'user5', N'123456', 3, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (7, N'user6', N'123456', 3, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (9, N'user8', N'123456', 2, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (10, N'user7', N'123456', 2, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (11, N'user10', N'123456', 2, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (12, N'user20', N'123456', 2, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (13, N'user21', N'123456', 2, 0)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (14, N'user22', N'123456', 2, 0)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (15, N'letrungtan', N'123456', 2, 1)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (16, N'user222', N'123456', 2, 0)
INSERT [dbo].[Account] ([AId], [account], [password], [role], [Verify]) VALUES (17, N'usermmm', N'123456', 2, 0)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Building] ON 

INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (1, N'Tòa A', 1)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (2, N'Tòa B', 1)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (3, N'Tòa C', 1)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (4, N'Tòa D', 1)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (5, N'Tòa E', 1)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (6, N'Tòa F', 1)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (7, N'Tòa G', 0)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (8, N'Tòa w', 0)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (9, N'Tòa F', 0)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (10, N'Tòa G', 0)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (11, N'Tòa W', 0)
INSERT [dbo].[Building] ([Bid], [name], [status]) VALUES (12, N'Tòa G', 1)
SET IDENTITY_INSERT [dbo].[Building] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CaId], [name], [type], [isDelete]) VALUES (1, N'Canh ', 1, 1)
INSERT [dbo].[Category] ([CaId], [name], [type], [isDelete]) VALUES (2, N'Chiên', 4, 1)
INSERT [dbo].[Category] ([CaId], [name], [type], [isDelete]) VALUES (3, N'Gỏi', 2, 1)
INSERT [dbo].[Category] ([CaId], [name], [type], [isDelete]) VALUES (4, N'Hầm', 2, 1)
INSERT [dbo].[Category] ([CaId], [name], [type], [isDelete]) VALUES (5, N'Hấp', 1, 1)
INSERT [dbo].[Category] ([CaId], [name], [type], [isDelete]) VALUES (6, N'Kho', 5, 1)
INSERT [dbo].[Category] ([CaId], [name], [type], [isDelete]) VALUES (7, N'Luộc', 3, 1)
INSERT [dbo].[Category] ([CaId], [name], [type], [isDelete]) VALUES (8, N'Xào ', 1, 1)
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Chef] ON 

INSERT [dbo].[Chef] ([ChId], [name], [phone], [email], [salary], [status], [address]) VALUES (1, N'Lê Văn Luyện a', N'066893689', N'toilaVu~@gmail.com', 5000000, 1, 4)
INSERT [dbo].[Chef] ([ChId], [name], [phone], [email], [salary], [status], [address]) VALUES (2, N'Phan Quang Đức', N'0690905896', N'ghj@gmail.com', 7000000, 1, 2)
INSERT [dbo].[Chef] ([ChId], [name], [phone], [email], [salary], [status], [address]) VALUES (3, N'Nguyễn Thị Q', N'0689664997', N'cvvvb@gmail.com', 6000000, 0, 3)
INSERT [dbo].[Chef] ([ChId], [name], [phone], [email], [salary], [status], [address]) VALUES (4, N'Lê Thị Hoa ', N'0168634169', N'thucntse162150@fpt.edu.vn', 5000000, 0, 5)
INSERT [dbo].[Chef] ([ChId], [name], [phone], [email], [salary], [status], [address]) VALUES (5, N'Lê Thị  Trà', N'0168634165', N'thu0@fpt.edu.vn', 5000000, 1, 5)
INSERT [dbo].[Chef] ([ChId], [name], [phone], [email], [salary], [status], [address]) VALUES (6, N'Hồ Hồng Quân', N'0336859466', N'toidaidot111@fpt.edu.vn', 2000000, 1, 11)
INSERT [dbo].[Chef] ([ChId], [name], [phone], [email], [salary], [status], [address]) VALUES (7, N'Lê Thị Hoa Tràaaaa', N'12312321321', N'thucntse162150@fpt.edu.vn', 123333, 0, 12)
SET IDENTITY_INSERT [dbo].[Chef] OFF
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([CuId], [name], [phone], [address], [email], [AccountId]) VALUES (1, N'Lê Văn A', N'01697995563', 4, N'abc@gmail.com', 2)
INSERT [dbo].[Customer] ([CuId], [name], [phone], [address], [email], [AccountId]) VALUES (2, N'Nguyễn Văn B', N'0168494631', 2, N'saw@gmail.com', 5)
INSERT [dbo].[Customer] ([CuId], [name], [phone], [address], [email], [AccountId]) VALUES (3, N'Nguyễn Văn c', N'0909058461', 3, N'rty@gmail.com', 4)
INSERT [dbo].[Customer] ([CuId], [name], [phone], [address], [email], [AccountId]) VALUES (4, N'Nguyễn Thị Hà', N'0364945891', 4, N'WQW@gmail.com', 10)
INSERT [dbo].[Customer] ([CuId], [name], [phone], [address], [email], [AccountId]) VALUES (5, N'Thai Vu', N'0168634169', 5, N'toilavu@gmail.com', 11)
INSERT [dbo].[Customer] ([CuId], [name], [phone], [address], [email], [AccountId]) VALUES (6, N'Thai Vu..', N'12312321321', 5, N'thucntse162150@fpt.edu.vn', 12)
INSERT [dbo].[Customer] ([CuId], [name], [phone], [address], [email], [AccountId]) VALUES (7, N'asd', N'06265165135', 4, N'thucntse162150@fpt.edu.vn', 13)
INSERT [dbo].[Customer] ([CuId], [name], [phone], [address], [email], [AccountId]) VALUES (8, N'asd', N'0168634169', 3, N'thucntse162150@fpt.edu.vn', 14)
INSERT [dbo].[Customer] ([CuId], [name], [phone], [address], [email], [AccountId]) VALUES (9, N'asd', N'06556565866', 2, N'thucntse162150@fpt.edu.vn', 15)
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[Food] ON 

INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (1, N'Canh bí đỏ hầm xương', N'Hàm lượng chất khoáng, vitamin,.. giúp mắt khỏe hơn.Có giá trị dinh dưỡng cao, ít calo.Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp', 1, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (2, N'Canh cà chua thịt bò', N'Hàm lượng chất khoáng, vitamin,.. giúp mắt khỏe hơn.Có giá trị dinh dưỡng cao, ít calo.Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp', 1, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (3, N'Canh khổ qua nhồi thịt', N'Hàm lượng chất khoáng, vitamin,.. giúp mắt khỏe hơn.Có giá trị dinh dưỡng cao, ít calo.Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp', 1, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (4, N'Canh rau củ hầm xương', N'Hàm lượng chất khoáng, vitamin,.. giúp mắt khỏe hơn.Có giá trị dinh dưỡng cao, ít calo.Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp', 1, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (5, N'Canh rau dền tôm', N'Hàm lượng chất khoáng, vitamin,.. giúp mắt khỏe hơn.Có giá trị dinh dưỡng cao, ít calo.Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp', 1, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (6, N'Canh rau cải thịt bằm', N'Hàm lượng chất khoáng, vitamin,.. giúp mắt khỏe hơn.Có giá trị dinh dưỡng cao, ít calo.Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp', 1, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (7, N'Canh chua cá lóc', N'Hàm lượng chất khoáng, vitamin,.. giúp mắt khỏe hơn.Có giá trị dinh dưỡng cao, ít calo.Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp', 1, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (8, N'Đậu ve cà rốt xào thịt', N'Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp và mức cholesterol. Tăng cường hệ miễn dịch cho cơ thể', 2, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (9, N'Rau muống xào tỏi', N'Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp và mức cholesterol. Tăng cường hệ miễn dịch cho cơ thể', 2, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (10, N'Thịt bò xào cần tây', N'Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp và mức cholesterol. Tăng cường hệ miễn dịch cho cơ thể', 2, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (11, N'Mực ống xào thơm', N'Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp và mức cholesterol. Tăng cường hệ miễn dịch cho cơ thể', 2, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (12, N'Lòng gà xào mướp', N'Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp và mức cholesterol. Tăng cường hệ miễn dịch cho cơ thể', 2, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (13, N'Thịt heo xào bông cải', N'Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp và mức cholesterol. Tăng cường hệ miễn dịch cho cơ thể', 2, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (14, N'Lòng non xào dưa chua', N'Chống các bệnh về tim mạch. Lượng chất xơ, vitamin C và Kali có trong bí đỏ làm giảm sự tăng huyết áp và mức cholesterol. Tăng cường hệ miễn dịch cho cơ thể', 2, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (15, N'Heo quay kho cải chua', N'Vitamin K bảo vệ sức khỏe tim mạch, ngăn chặn quá trình tích tụ canxi trong động mạch chủ, từ đó giảm khả năng cũng như nguy cơ gây ra bệnh tim mạch', 3, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (16, N'Cá nục kho cà', N'Giàu vitamin C, A, B, mangan,…mà trong quả thơm (khóm) còn chứa một loại enzym có lợi giúp phá vỡ các protein phức tạp, hấp thu các chất khác được tốt hơn, đồng thời chống viêm hiệu quả', 3, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (17, N'Cá lóc kho tộ', N'Hàm lượng cao các axit amin thiết yếu và một lượng axit béo tốt có thể trực tiếp cải thiện sự phát triển của mô, chữa lành vết thương, bổ sung chất dinh dưỡng như thực phẩm bổ sung và dược phẩm', 3, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (18, N'Gà kho gừng', N'Nhiều các vitamin A, B1, B2, C, E, axit, canxi, sắt, chất béo… có khả năng bồi dưỡng cho người bị bệnh lâu ngày, dạ dày bị phong hàn, suy yếu không hấp thụ được thức ăn, trừ phong hàn, cảm cúm.', 3, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (19, N'Thịt kho tàu', N'Phù hợp nhất với trẻ em, vì nó mềm, dễ ăn, giàu năng lượng, đạm, chất béo, phù hợp với nhu cầu phát triển của bé', 3, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (20, N'Cá trắm kho riềng', N'Chất đạm, chất béo không no, muối khoáng và nhiều vitamin các loại bao gồm vitamin A, vitamin D, vitamin B, vitamin E, omega – 3,Tăng cường sức khỏe tim mạch, giảm nguy cơ mắc các bệnh về mạch máu', 3, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (21, N'Thịt kho tôm', N'Giàu Omega-3 tốt cho não, Selen: Selen hoạt động như một chất chống oxy hóa mạnh,Vitamin B12: Giữ cho các tế bào thần kinh và máu của cơ thể khỏe mạnh,', 3, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (22, N'Gà hầm hạt sen', N'Hạt sen còn chứa alcaloid và flavonoid cùng nhiều khoáng chất như Canxi, Kẽm, Magie, Sắt, Kali… giúp tăng cường hệ thống miễn dịch của cơ thể', 4, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (23, N'Gà hầm khoai tây', N'Khoai tây chứa nhiều chất xơ thô tốt cho hệ tiêu hóa. Cà rốt có tác dụng bổ mắt, chữa quáng gà rất hiệu quả', 4, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (24, N'Xương bò hầm atiso', N'Nước hầm xương bò giúp tăng cường sức khỏe xương khớp nhờ thành phần Glucosamine.', 4, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (25, N'Nấm đông trùng hạ thảo hầm gà', N'Phục hồi sức khỏe, tăng cường sinh lý, tốt cho người bệnh tim, ngăn ngừa sự phát triển của tế bào ung thư, giảm căng thẳng, mệt mỏi và cải thiện giấc ngủ', 4, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (26, N'Thịt bò hầm nấm', N'Bổ sung vitamin B6 và Protein: giúp cơ thể xây dựng khả năng miễn dịch, phục hồi cơ thể sau những hoạt động cường độ cao.giàu sắt: giúp bổ sung lượng máu cho cơ thể và phòng tránh thiếu máu.', 4, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (27, N'Bồ câu hầm sen', N'Cung cấp protein, chất béo, Calci, Magie, Phospho, vitamin E, vitamin nhóm B, vitamin A… Chính nhờ những thành phần kể trên, thịt bồ câu có tác dụng kích thích tiêu hóa', 4, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (28, N'Gỏi ngó sen tôm thịt', N'Cung cấp nhiều vitamin A, B12, B6, C, E,... Tốt cho hệ tiêu hoá, cải thiện sức khỏe tim mạch và xương cũng như điều chỉnh huyết áp và đường huyết
', 5, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (29, N'Nộm thịt gà hoa chuối', N'Chứa nhiều chất như Calorie, chất béo, protein, vitamin A, C,.. Giúp tăng cường sức khỏe tinh thần cũng như tăng cường miễn dịch và hỗ trợ giảm cân
', 5, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (30, N'Nộm xoài hải sản', N'Giàu vitamin C, giúp tăng sức đề kháng, hỗ trợ hấp thu sắt, hấp thu canxi vào bẹ xương tốt hơn. Ngoài ra, ăn xoài xanh còn giúp tăng sản sinh tế bào máu', 5, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (31, N'Nộm tai heo dưa chuột
', N'Bù nước và bổ sung vitamin cho cơ thể, Tốt cho tiêu hoá, làm dịu cơn đau cơ khớp, tăng khả năng miễn dịch nhờ sở hữu vitamin A, B, C', 5, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (32, N'Nộm hoa chuối bắp bò
', N'Hoa chuối rất giàu sắt. Sắt là nguồn khoáng chất chính cần để duy trì việc sản sinh các tế bào máu khỏe mạnh. Vì vậy, hoa chuối có thể kích thích sản sinh các tế bào hồng cầu, từ đó chữa thiếu máu', 5, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (33, N'Nộm rau má
', N'Chữa các bệnh về tĩnh mạch, Phục hồi vết thương, Giảm lo âu, Cải thiện khả năng nhận thức, Giúp hệ tiêu hóa khỏe mạnh', 5, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (34, N'Nộm đu đủ bò khô
', N'Chứa Calo, Carbohydrate, Chất xơ, Chất đạm, Vitamin C, Vitamin A. Cải thiện sức khỏe tim mạch.Cải thiện chức năng tiêu hóa', 5, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (35, N'Bắp cải luộc chấm nước mắm trứng', N'Cung cấp vitamin A, C, K, chất xơ,.. Có lợi cho dạ dày. Giảm các dấu hiệu lão hóa. Tốt cho làn da', 6, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (36, N'Thịt heo luộc chấm nước mắm', N'Cung cấp photpho, kali, nicaxin, vitamin B6, vitamin B12, kẽm… Giúp duy trì cơ bắp, Cải thiện hiệu suất tập thể dục, ..', 6, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (37, N'Rau bí luộc chấm xì dầu', N'Bên cạnh khả năng tăng cường chức năng hệ miễn dịch và tim mạch, việc ăn rau bí thường xuyên cũng góp phần làm cho răng và xương trở nên chắc khỏe hơn.', 6, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (38, N'Cá lóc hấp sả chanh', N'Cung cấp các nhóm dinh dưỡng như protein, lipid, vitamin A,.. Có tác dụng chữa cảm lạnh, hỗ trợ điều trị tăng huyết áp, Thanh nhiệt, điều hòa dạ dày', 7, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (39, N'Gà hấp gia vị
', N'Những lợi ích nổi trội nhất của thịt gà là tăng cường cơ bắp, làm xương chắc khỏe và giúp tăng cân khỏe mạnh', 7, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (40, N'Trứng cuộn tôm hấp
', N'Cung cấp nguồn Calo lớn, chứa nhiều chất đạm, chất béo, cholesterol, vitamin A, B tổng hợp, vitamin D, E, K', 7, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (41, N'Tôm hấp nước sốt cay
', N'Tôm cung cấp canxi, magie, vitamin D. duy trì sức khỏe xương và giúp ngăn ngừa vấn đề sức khỏe như loãng xương', 7, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (42, N'Tôm chiên xù', N'Tôm cung cấp canxi, magie, vitamin D. duy trì sức khỏe xương và giúp ngăn ngừa vấn đề sức khỏe như loãng xương, viêm xương khớp', 8, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (43, N'MỰC CHIÊN MẮM', N'Mực là nguồn cung cấp vitamin B12 khá dồi dào, một trong những chất dinh dưỡng đã được chứng minh giúp làm giảm nồng độ homocysteine', 8, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (44, N'NEM RÁN ĂN KÈM BÚN VÀ RAU SỐNG', N'Nem chua khi rán lên khi ăn với hàm lượng vừa đủ cũng sẽ không bị béo phì. Lượng calo mà bạn cần nạp vào mỗi ngày thường nằm ở khoảng 2000 calo/ngày.', 8, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (45, N'Cánh gà chiên bơ', N'cánh gà,bơ vàng,tỏi,dưa leo,cà chua,bột chiên giòn', 8, 1)
INSERT [dbo].[Food] ([FId], [name], [nutrition], [categoryId], [status]) VALUES (46, N'THỊT BA CHỈ QUAY GIÒN BÌ', N'Có vitamin B1 đóng vai trò chuyển hóa Carbohydrate trong cơ thể Hàm lượng vitamin B2 trong thực phẩm này có tác dụng giải độc cho cơ thể', 8, 1)
SET IDENTITY_INSERT [dbo].[Food] OFF
GO
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (1, 1)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (2, 2)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (3, 3)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (4, 4)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (5, 5)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (6, 6)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (7, 7)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (8, 1)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (8, 8)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (9, 2)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (9, 9)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (10, 3)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (10, 10)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (11, 4)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (11, 10)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (11, 11)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (12, 12)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (15, 1)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (16, 2)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (16, 3)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (17, 4)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (17, 6)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (18, 5)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (18, 8)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (19, 7)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (19, 9)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (21, 10)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (21, 11)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (22, 1)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (22, 7)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (22, 12)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (24, 5)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (24, 6)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (26, 4)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (28, 1)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (28, 8)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (29, 2)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (29, 9)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (31, 11)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (32, 7)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (32, 12)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (34, 10)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (35, 2)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (35, 6)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (36, 3)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (36, 5)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (37, 12)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (38, 8)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (39, 9)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (39, 11)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (40, 5)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (40, 9)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (41, 11)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (42, 12)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (43, 3)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (44, 7)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (45, 4)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (45, 6)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (45, 10)
INSERT [dbo].[FoodItem] ([foodId], [mealId]) VALUES (46, 8)
GO
SET IDENTITY_INSERT [dbo].[FoodStyle] ON 

INSERT [dbo].[FoodStyle] ([FsId], [type]) VALUES (1, N'Bắc ')
INSERT [dbo].[FoodStyle] ([FsId], [type]) VALUES (2, N'Trung')
INSERT [dbo].[FoodStyle] ([FsId], [type]) VALUES (3, N'Nam')
SET IDENTITY_INSERT [dbo].[FoodStyle] OFF
GO
SET IDENTITY_INSERT [dbo].[Image] ON 

INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (1, N'canhbido.jpg', NULL, 1)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (2, N'canhcachua.jpg', NULL, 2)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (3, N'canhkhoqua.jpg', NULL, 3)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (4, N'canhraucu.jpg', NULL, 4)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (5, N'canhrauden.jpg', NULL, 5)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (6, N'canhraucai.jpg', NULL, 6)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (7, N'canhcaloc.jpg', NULL, 7)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (8, N'dauve.jpg', NULL, 8)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (9, N'raumuong.jpg', NULL, 9)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (10, N'cantay.jpg', NULL, 10)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (11, N'mucthom.jpg', NULL, 11)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (12, N'longgamuop.jpg', NULL, 12)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (13, N'bongcai.jpg', NULL, 13)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (14, N'longduachua.jpg', NULL, 14)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (15, N'heoquaykhocaichua.jpg', NULL, 15)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (16, N'canuckhoca.jpg', NULL, 16)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (17, N'calockhoto.jpg', NULL, 17)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (18, N'gakhogung.jpg', NULL, 18)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (19, N'thitkhotau.jpg', NULL, 19)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (20, N'catramkho.jpg', NULL, 20)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (21, N'thitkhotom.jpg', NULL, 21)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (22, N'gahamhatsen.jpg', NULL, 22)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (23, N'gahamkhoaitay.jpg', NULL, 23)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (24, N'xuongboham.jpg', NULL, 24)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (25, N'dongtrunghathao.jpg', NULL, 25)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (26, N'thitbohamnam.jpg', NULL, 26)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (27, N'bocauhamsen.jpg', NULL, 27)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (28, N'goitom.jpg', NULL, 28)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (29, N'nomthitgahoachuoi.jpg', NULL, 29)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (30, N'nomxoaihaisan.jpg', NULL, 30)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (31, N'nomtaiheoduachuot.jpg', NULL, 31)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (32, N'nomhoachuoibapbo.jpg', NULL, 32)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (33, N'nomrauma.jpg', NULL, 33)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (34, N'nomdudubokho.jpg', NULL, 34)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (35, N'bapcailuoc.jpg', NULL, 35)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (36, N'thitheoluoc.jpg', NULL, 36)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (37, N'raubiluoc.jpg', NULL, 37)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (38, N'calochap.jpg', NULL, 38)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (39, N'gahapgiavi.jpg', NULL, 39)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (40, N'trungcuontomhap.jpg', NULL, 40)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (41, N'tomhapnuocsotcay.jpg', NULL, 41)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (42, N'tomchienxu.jpg', NULL, 42)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (43, N'mucchienmam.jpg', NULL, 43)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (44, N'nemran.jpg', NULL, 44)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (45, N'canhgachien.jpg', NULL, 45)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (46, N'bachiquay.jpg', NULL, 46)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (47, N'mamcom.jpg', 1, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (48, N'mamcom.jpg', 2, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (49, N'mamcom.jpg', 3, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (50, N'mamcom.jpg', 4, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (51, N'mamcom.jpg', 5, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (52, N'mamcom.jpg', 6, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (53, N'mamcom.jpg', 7, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (54, N'mamcom.jpg', 8, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (55, N'mamcom.jpg', 9, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (56, N'mamcom.jpg', 10, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (57, N'mamcom.jpg', 11, NULL)
INSERT [dbo].[Image] ([IId], [url], [mealId], [foodId]) VALUES (58, N'mamcom.jpg', 12, NULL)
SET IDENTITY_INSERT [dbo].[Image] OFF
GO
SET IDENTITY_INSERT [dbo].[MealGroup] ON 

INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (1, 135000, 6, N'Mâm cơm 1', 5, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (2, 130000, 1, N'Mâm cơm 2', 8, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (3, 130000, 1, N'Mâm cơm 3', 0, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (4, 135000, 1, N'Mâm cơm 4', 5, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (5, 135000, 2, N'Mâm cơm 5', 0, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (6, 135000, 2, N'Mâm cơm 6', 0, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (7, 125000, 2, N'Mâm cơm 7', 0, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (8, 120000, 2, N'Mâm cơm 8', 8, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (9, 124000, 3, N'Mâm cơm 9', 1, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (10, 123000, 3, N'Mâm cơm 10', 0, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (11, 122000, 3, N'Mâm cơm 11', 0, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
INSERT [dbo].[MealGroup] ([MId], [price], [chefId], [name], [soldNumber], [detail], [status]) VALUES (12, 120000, 3, N'Mâm cơm 12', 0, N'Mâm cơm truyền thống Việt Nam chứa nhiều chất dinh dưỡng, hương vị ngọt béo của thịt kho , cảm giác mát mẻ từ canh bầu,...', 1)
SET IDENTITY_INSERT [dbo].[MealGroup] OFF
GO
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (1, 5, 120000, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (1, 20, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (1, 23, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (1, 24, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (1, 25, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (1, 26, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (2, 20, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (2, 23, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (2, 24, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (3, 20, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (3, 23, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (4, 20, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (4, 23, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (4, 24, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (4, 25, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (5, 5, 125555, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (5, 20, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (5, 23, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (5, 25, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (5, 26, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (6, 23, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (6, 24, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (6, 25, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (6, 26, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (7, 3, 120000, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (7, 24, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (7, 25, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (7, 26, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (8, 3, 120000, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (8, 23, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (8, 24, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (8, 25, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (8, 26, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (9, 3, 120000, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (9, 23, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (9, 24, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (9, 25, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (10, 3, 125000, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (10, 26, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (11, 3, 125000, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (11, 26, NULL, NULL)
INSERT [dbo].[MealGroupSession] ([mealId], [sessionId], [price], [note]) VALUES (12, 3, 125000, NULL)
GO
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (1, 1, N'it cay', 2, 1, 120000, 2, 1, 1, N'asd', NULL, NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (2, 2, N'it cay', 2, 9, 120000, 1, 1, 1, N'asd', NULL, NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (3, 3, N'it cay', 1, 9, 130000, 1, 1, 1, N'asd', NULL, NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (4, 4, N'ham nong!!', 8, 1, 120000, 0, 3, 1, N'asd', NULL, NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (5, 5, N'12312321', 10, 1, 125000, 0, 3, 1, N'asd', NULL, NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (6, 3, N'bo cay nhieu nha', 6, 3, 125000, 1, 1, 1, N'asd', NULL, NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (7, 3, N'them cay nhieu nha', 11, 3, 125000, 2, 3, 1, N'asd', NULL, NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (8, 2, N'bo cay nhieu nha', 4, 3, 120000, 0, 1, 1, N'asd', NULL, NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (9, 4, N'it cay', 12, NULL, 125000, 0, 3, 2, N'asd', N'03653165133', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (10, 4, N'it cay', 12, NULL, 125000, 0, 3, 2, N'asd', N'03653165133', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (11, 1, N'it cay', 5, NULL, 125555, 2, 5, 1, N'asd', N'0168634169', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (12, 1, N'it cay', 5, NULL, 125555, 0, 5, 1, N'asd', N'0168634169', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (13, 1, N'it cay', 5, NULL, 125555, 0, 5, 1, N'asd', N'0168634169', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (15, 1, N'ham nong!!', 2, NULL, 120000, 0, 1, 1, N'asd', N'0168634169', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (16, 1, N'', 5, NULL, 125555, 2, 5, 1, N'asd', N'0168634169', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (17, 1, N'it cay ', 6, NULL, 125000, 0, 1, 2, N'asd', N'0168634169', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (18, 1, N'it cay ', 6, NULL, 125000, 0, 1, 2, N'asd', N'0168634169', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (19, 1, N'it cay ', 6, NULL, 125000, 0, 1, 2, N'asd', N'0168634169', NULL)
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (20, 1, N'them cay nhieu nha', 3, NULL, 120000, 0, 1, 1, N'asd', N'0168634169', N'thucntse162150@fpt.edu.vn')
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (21, 1, N'them cay nhieu nha', 3, NULL, 120000, 0, 1, 1, N'asd', N'0168634169', N'thucntse162150@fpt.edu.vn')
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (22, 1, N'them cay nhieu nha', 3, NULL, 120000, 0, 1, 1, N'asd', N'0168634169', N'thucntse162150@fpt.edu.vn')
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (23, 1, N'them cay nhieu nha', 3, NULL, 120000, 0, 1, 1, N'asd', N'0168634169', N'thucntse162150@fpt.edu.vn')
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (24, 1, N'', 6, NULL, 135000, 0, 24, 1, N'Tòa Gc', N'0168634169', N'luffykiofa@gmail.com')
INSERT [dbo].[Order] ([OId], [address], [orderDetail], [mealId], [customerId], [price], [status], [sessionId], [PId], [customerName], [phone], [email]) VALUES (25, 3, N'', 8, NULL, 120000, 0, 24, 1, N'Thai Vu.', N'12312321321', N'thucntse162150@fpt.edu.vn')
SET IDENTITY_INSERT [dbo].[Order] OFF
GO
SET IDENTITY_INSERT [dbo].[Payment] ON 

INSERT [dbo].[Payment] ([PId], [type], [detail]) VALUES (1, N'Ship đến tận tòa ', NULL)
INSERT [dbo].[Payment] ([PId], [type], [detail]) VALUES (2, N'Tại quán', NULL)
SET IDENTITY_INSERT [dbo].[Payment] OFF
GO
SET IDENTITY_INSERT [dbo].[Session] ON 

INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (1, N'11-13', N'23/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (3, N'17-19', N'24/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (5, N'11-13', N'24/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (6, N'17-19', N'23/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (7, N'11-13', N'20/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (18, N'13-14', N'20/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (20, N'21-22', N'21/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (21, N'13-14', N'22/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (23, N'11-12', N'26/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (24, N'16-18', N'26/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (25, N'15-16', N'28/10', 1)
INSERT [dbo].[Session] ([SId], [timeline], [day], [status]) VALUES (26, N'17-19', N'28/10', 1)
SET IDENTITY_INSERT [dbo].[Session] OFF
GO
ALTER TABLE [dbo].[Chef]  WITH CHECK ADD FOREIGN KEY([address])
REFERENCES [dbo].[Building] ([Bid])
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD FOREIGN KEY([address])
REFERENCES [dbo].[Building] ([Bid])
GO
ALTER TABLE [dbo].[Food]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[Category] ([CaId])
GO
ALTER TABLE [dbo].[FoodItem]  WITH CHECK ADD FOREIGN KEY([foodId])
REFERENCES [dbo].[Food] ([FId])
GO
ALTER TABLE [dbo].[FoodItem]  WITH CHECK ADD FOREIGN KEY([mealId])
REFERENCES [dbo].[MealGroup] ([MId])
GO
ALTER TABLE [dbo].[Image]  WITH CHECK ADD FOREIGN KEY([foodId])
REFERENCES [dbo].[Food] ([FId])
GO
ALTER TABLE [dbo].[Image]  WITH CHECK ADD FOREIGN KEY([mealId])
REFERENCES [dbo].[MealGroup] ([MId])
GO
ALTER TABLE [dbo].[Like]  WITH CHECK ADD FOREIGN KEY([chefId])
REFERENCES [dbo].[Chef] ([ChId])
GO
ALTER TABLE [dbo].[Like]  WITH CHECK ADD FOREIGN KEY([customerId])
REFERENCES [dbo].[Customer] ([CuId])
GO
ALTER TABLE [dbo].[MealGroup]  WITH CHECK ADD FOREIGN KEY([chefId])
REFERENCES [dbo].[Chef] ([ChId])
GO
ALTER TABLE [dbo].[MealGroupSession]  WITH CHECK ADD FOREIGN KEY([mealId])
REFERENCES [dbo].[MealGroup] ([MId])
GO
ALTER TABLE [dbo].[MealGroupSession]  WITH CHECK ADD FOREIGN KEY([sessionId])
REFERENCES [dbo].[Session] ([SId])
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([address])
REFERENCES [dbo].[Building] ([Bid])
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([customerId])
REFERENCES [dbo].[Customer] ([CuId])
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([mealId])
REFERENCES [dbo].[MealGroup] ([MId])
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([PId])
REFERENCES [dbo].[Payment] ([PId])
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([sessionId])
REFERENCES [dbo].[Session] ([SId])
GO
USE [master]
GO
ALTER DATABASE [cmn] SET  READ_WRITE 
GO