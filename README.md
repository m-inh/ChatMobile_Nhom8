# ChatMobile_Nhom8

Cấu trúc thư mục: 
	client/nhom8/com/avatar: chứa các class xử lí bên trong android
		adapter: chứa các adapter
		models: chứa các class tạo ra các đối tượng phụ (item, ...)
		(xem trong prj để thấy rõ hơn)
		....
		activity để mặc định ở bên ngoài
	models: chứa các file để giao tiếp dữ liệu với sever (vì tên package của class 2 bên phải giống nhau)

Cách đặt tên:
	java: 
		class: Viết hoa chữ cái đầu của mỗi tiếng, đặt tên có ý nghĩa, thể hiện nó là đối tượng của lớp nào (activity, fragment, adapter .....)
			vd: LoginActivity, LoginFragment, ....
		biến: chữ cái đầu của biến viết thường, mỗi tiếng trong từ ngăn cách bởi chữ cái viết hoa
			vd: mContext, userDb, ...
		Hằng: viết hoa toàn bộ kí tự, mỗi tiếng ngăn cách bởi dấu gạch dưới "_"
			vd: REQUESTCODE_LOGINACTIVIY
		hàm: chữ cái đầu hàm viết thường, mỗi tiếng ngăn cách bởi kí tự viết hoa
			vd: getContext(), logOut(), ....

	res: 
		layout: Tên viết thường hết, tên loại đặt ở đầu, 2 phần ngăn cách nhau bởi dấu "_"
			vd: activity_login, fragment_setting, item_contact, ....
		id: viết thường hết, tên rút gọn của view đắt ở đầu, ngăn cách với tên chức năng bởi dấu "_"
			lấy các tiếng của view để làm tên rút gọn của nó: LinearLayout: ll, Button: btn, EditText: edt,....
			vd: btn_send, edt_name, ....
		string, color: đặt tên có ý nghĩa là được, mỗi tiếng ngăn cách nhau bởi dấu "_"
