Câu 1: Tạo đầy đủ lược đồ cơ sở dữ liệu quan hệ như mô tả ở trên. Sinh viên tự định nghĩa kiểu dữ liệu cho các cột  (0.5 điểm)


Câu 2: Chèn toàn bộ dữ liệu mẫu đã được minh họa ở trên vào tất cả các bảng một cách chính xác (0.5 điểm)

***** Lưu ý: Nếu không hoàn thành yêu cầu của câu 1 và câu 2 thì sẽ không được chấm và tính điểm cho các yêu cầu tiếp theo *****

Câu 3: Liệt kê những phòng karaoke chứa được số lượng tối đa dưới 20 khách (0.5 điểm)

select maphong from phong where SoKhachToiDa < 20 

Câu 4: Liệt kê thông tin của các dịch vụ có đơn vị tính là "Chai" với đơn giá nhỏ hơn 20.000 VNĐ 
và các dịch vụ có đơn vị tính là "Lon" với đơn giá lớn hơn 30.000 VNĐ (0.5 điểm)

select * from dichvu where DonViTinh = 'Chai' and DonGia < 20000
union
select * from dichvu where DonViTinh = 'Lon' and DonGia > 30000

Câu 5: Liệt kê thông tin của các phòng karaoke có mã phòng bắt đầu bằng cụm từ "VIP" (0.5 điểm)

select * from phong where MaPhong like 'VIP%'

Câu 6: Liệt kê thông tin của toàn bộ các dịch vụ, yêu cầu sắp xếp giảm dần theo đơn giá (0.5 điểm)

select * from dichvu 
group by DonGia desc

Câu 7: Đếm số hóa đơn có trạng thái là "Chưa thanh toán" và có thời gian bắt đầu sử dụng nằm trong ngày hiện tại (0.5 điểm)

select mahd, count(mahd) from HOADON
where TrangThaiHD = 'Chua thanh toan' and day(ThoiGianBatDauSD) = 3 


Câu 8: Liệt kê địa chỉ của toàn bộ các khách hàng với yêu cầu mỗi địa chỉ được liệt kê một lần duy nhất (0.5 điểm)

select distinct(diachi) from KHACHHANG

Câu 9: Liệt kê MaHD, MaKH, TenKH, DiaChi, MaPhong, DonGia (Tiền giờ), ThoiGianBatDauSD, ThoiGianKetThucSD 
của tất cả các hóa đơn có trạng thái là "Đã thanh toán" (0.5 điểm)

select HOADON.MaHD, HOADON.MaKH, KHACHHANG.TenKH, diachi, HOADON.MaPhong, MUCTIEUGIO.DonGia, HOADON.ThoiGianBatDauSD, HOADON.ThoiGianKetThucSD
from KHACHHANG inner join HOADON on KHACHHANG.MaKH = HOADON.MaKH
inner join MUCTIEUGIO on HOADON.MaTienGio = MUCTIEUGIO.Matiengio
where TrangThaiHD = 'Da thanh toan'


Câu 10: Liệt kê MaKH, TenKH, DiaChi, MaHD, TrangThaiHD của tất cả các hóa đơn với yêu cầu 
những khách hàng chưa từng có một hóa đơn nào thì cũng liệt kê thông tin những khách hàng đó ra (0.5 điểm)

select KHACHHANG.MaKH, KHACHHANG.TenKH, KHACHHANG.DiaChi, HOADON.TrangThaiHD, HOADON.MaHD
from KHACHHANG left join HOADON on KHACHHANG.MaKH = HOADON.MaHD

Câu 11: Liệt kê thông tin của các khách hàng đã từng sử dụng dịch vụ "Trái cây" hoặc từng sử dụng phòng karaoke có mã phòng là "VIP07" (0.5 điểm)

select HOADON.MaKH from HOADON inner join CHITIET_SUDUNGDV on HOADON.MaHD = CHITIET_SUDUNGDV.MaDV
where CHITIET_SUDUNGDV.MaDV = 'Trai Cay' or HOADON.MaPhong = 'VIP07'

Câu 12: Liệt kê thông tin của các khách hàng chưa từng sử dụng dịch vụ hát karaoke lần nào cả (0.5 điểm)

select MaKH, TenKH from KHACHHANG 
where MaKH not in (
	select MaKH from HOADON 
)

Câu 13: Liệt kê thông tin của các khách hàng đã từng sử dụng dịch vụ hát karaoke và chưa từng sử dụng dịch vụ nào khác kèm theo (0.5 điểm)

select khachhang.MaKH, TenKH from KHACHHANG inner join HOADON on KHACHHANG.MaKH = HOADON.MaKH
where HOADON.MaHD not in (
	select MaHD from CHITIET_SUDUNGDV 
)


Câu 14: Liệt kê thông tin của những khách hàng đã từng hát karaoke vào năm "2014" nhưng chưa từng hát karaoke vào năm "2015"  (0.5 điểm)

select makh from HOADON where year(ThoiGianBatDauSD) = 2014
except
select makh from HOADON where year(ThoiGianBatDauSD) = 2015

Câu 15: Hiển thị thông tin của những khách hàng có số lần hát karaoke nhiều nhất tính từ đầu năm 2014 đến hết năm 2014 (0.5 điểm)

select top 1 KHACHHANG.MaKH, KHACHHANG.TenKH from KHACHHANG inner join HOADON on KHACHHANG.MaKH = HOADON.MaKH
where YEAR (ThoiGianBatDauSD) = 2014
group by khachhang.makh, khachhang.tenkh
order by count(MaHD) desc

Câu 16: Đếm tổng số lượng loại dịch vụ đã được sử dụng trong năm 2014 
với yêu cầu chỉ thực hiện tính đối với những loại dịch vụ có đơn giá từ 50.000 VNĐ trở lên (0.5 điểm)

select DICHVU.MaDV, DICHVU.TenDV, count(CHITIET_SUDUNGDV.MaHD) from DICHVU 
inner join CHITIET_SUDUNGDV on DICHVU.MaDV = CHITIET_SUDUNGDV.MaDV
inner join HOADON on CHITIET_SUDUNGDV.MaHD = HOADON.MaHD
where (YEAR(ThoiGianBatDauSD) = 2014 and year(ThoiGianKetThucSD) = 2014) and DonGia > 50000
group by  DICHVU.MaDV, DICHVU.TenDV

Câu 17: Liệt kê MaKH, TenKH, MaSoThue của khách hàng có địa chỉ là "Hải Châu" và chỉ mới hát karaoke một lần duy nhất, 
kết quả được sắp xếp giảm dần theo TenKH (0.5 điểm)

select KHACHHANG.MaKH, KHACHHANG.TenKH, KHACHHANG.MaSoThue from KHACHHANG join HOADON on KHACHHANG.MaKH = HOADON.MaKH
where DiaChi = 'Hai Chau'
group by KHACHHANG.MaKH, KHACHHANG.TenKH, KHACHHANG.MaSoThue
having count(KHACHHANG.MaKH) = 1
order by TenKH desc

Câu 18: Cập nhật cột TrangThaiHD trong bảng HOADON thành giá trị "Đã hết hạn" 
đối với những khách hàng có địa chỉ là "Hải Châu" và có ThoiGianKetThucSD trước ngày 31/12/2015 (0.5 điểm)

update HOADON 
set TrangThaiHD = 'Da het han'
where makh = (select KHACHHANG.MaKH from KHACHHANG join HOADON on KHACHHANG.makh = HOADON.MaKH 
where KHACHHANG.DiaChi = 'Hai Chau' and YEAR(ThoiGianKetThucSD) < 2016 )

Câu 19: Cập nhật cột MoTa trong bảng PHONG thành giá trị "Được sử dụng nhiều" 
cho những phòng được sử dụng từ 5 lần trở lên trong tháng 5 năm 2015  (0.5 điểm)

update phong 
set MoTa = 'duoc su dung nhieu'
where MaPhong = (
	select PHONG.MaPhong from phong join HOADON on phong.MaPhong = HOADON.MaPhong 
	where (year(ThoiGianBatDauSD) = 2015 and month(ThoiGianBatDauSD) = 5) or (year(ThoiGianKetThucSD) = 2015 and month(ThoiGianKetThucSD) = 5)
	group by phong.MaPhong
	having count(MaHD) >5
)


Câu 20: Xóa những hóa đơn có ThoiGianBatDauSD trước ngày 20/11/2015 (0.5 điểm)


