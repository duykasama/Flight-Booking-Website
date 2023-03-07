use master
go

if exists(select name from master.dbo.sysdatabases where name = 'vnflight')
	alter database vnflight set offline with rollback immediate
	alter database vnflight set online
	drop database vnflight
go

create database vnflight
go

use vnflight
go

create table [admin](
	[name] varchar(30) primary key,
	[password] varchar(20) not null,
	email varchar(30),
	phone varchar(10)
)

create table [user](
	id int primary key identity(1,1),
	[name] varchar(20) not null,
	[password] varchar(20) not null,
	email varchar(30),
	phone varchar(10)
)

create table airport(
	id int primary key identity(1,1),
	name nvarchar(50)
)

create table flight(
	id int primary key identity(1,1),
	takeoff_time time not null,
	landing_time time not null,
	departure_date date not null,
	price bigint not null,
	airline_name nvarchar(50) not null,
	no_of_seats int not null,
	departure_id int not null foreign key references airport(id),
	destination_id int not null foreign key references airport(id),
	[status] int not null
)

create table invoice(
	id int primary key identity(1,1),
	[user_id] int not null foreign key references [user](id),
	flight_id int not null foreign key references flight(id),
	booking_date date not null,
	total_price bigint not null,
	purchase_status int not null
)

create table passenger_ticket(
	id int primary key identity(1,1),
	invoice_id int foreign key references invoice(id),
	firstname nvarchar(15) not null,
	lastname nvarchar(15) not null,
	luggage_weight float not null,
	luggage_price bigint not null,
	card_id varchar(15),
	gender varchar(10),
	nationality varchar(50),
	dob date
)

create table seat(
	seat_id int primary key identity(1,1),
	seat_number varchar(10) not null,
	flight_id int foreign key references flight(id),
	[passenger_ticket_id] int foreign key references [passenger_ticket](id),
	seat_status int not null
)

if object_id('pro_checkFlightStatus') is not null
	drop proc pro_checkFlightStatus
go

create proc pro_checkFlightStatus
as 
begin
	update flight
	set [status] = 1
	where (departure_date < getdate() or (departure_date = getdate() and takeoff_time < cast(getdate() as time))) and [status] = 0
end


--if object_id('pr_createInvoice') is not null
--	drop proc pr_createInvoice
--go

--create proc pr_createInvoice 
--	(@user_id int, @flight_id int, @total_price bigint)
--as 
--	begin transaction
--		declare @no_of_seats int
--		select @no_of_seats = count(seat_id) from seat where flight_id = @flight_id
--		if @no_of_seats >= (select top 1 no_of_seats from flight)
--			rollback;
--		else
--			begin
--				declare @id int
--				select top 1 @id = id from invoice order by id desc
--				if @id is null
--				set @id = 1
--				else set @id = @id + 1
--				insert into invoice values(@id, @user_id, @flight_id, getdate(), @total_price, 0) --0 is purchase status
--				commit
--			end


--if object_id('pr_createSeat') is not null
--	drop proc pr_createSeat
--go

--create proc pr_createSeat 
--	(@seat_number varchar(10), @flight_id int, @passenger_ticket_id int)
--as
--	begin transaction
--		declare @check_number varchar(10)
--		select @check_number = seat_number from seat where flight_id = @flight_id
--		if @check_number is null
--			begin
--				print 'aas'
--				declare @seat_id int
--				select top 1 @seat_id = seat_id from seat order by seat_id desc
--				if @seat_id is null
--					set @seat_id = 1
--				else
--					begin
--						set @seat_id = @seat_id + 1
--						insert into seat values(@seat_id, @seat_number, @flight_id, @passenger_ticket_id, 1)
--						commit
--					end
					
--			end
--		else 
--			begin
--			rollback
--			end