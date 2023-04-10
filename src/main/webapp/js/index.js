window.onload = () => {
	const petHome = document.getElementById("petHome");
	const pageHome = document.getElementsByClassName("pageHome")[0];
	const per = document.getElementById("per");
	let loggedInMemberID;

	// 初始化
	(function init() {
		getLoggedInMemberID();
		getPetList();
	})()

	// 取得登入會員ID
	function getLoggedInMemberID() {
		fetch(`${root}/GetLoggedInMemberID`).then(result => result.text()).then(memberID => {
			loggedInMemberID = parseInt(memberID)
		})
	}

	// 取得寵物列表
	function getPetList(page = 1, count = 6) {
		let url = `${root}/GetPets?page=${page}&count=${count}`;
		fetch(url).then(result => result.json()).then(paginatedPetInfo => {
			renderPetList(paginatedPetInfo.content);
			renderPage(paginatedPetInfo);
		})
	}

	// 渲染寵物列表
	function renderPetList(petList) {
		let output = "";
		for (let pet of petList) {
			output += `<div class="col">`;
			output += `<div class="card shadow-sm">`;
			output += `<img src="${root}/getPetPhoto?pID=${pet.pID}" class="w-100"`;
			output += `<div class="card-body">`;
			output += `<p class="card-text fs-3">${pet.name}</p>`;
			output += `<div class="d-flex justify-content-between align-items-center">`;

			output += `<button type="button" class="btn btn-sm btn-outline-secondary" pID=${pet.pID}>`;
			if (pet.likedByMemberIds.includes(loggedInMemberID)) {
				output += `<i class="fa-solid fa-heart fa-xl" style="color: #e51515;"></i>`;
			} else {
				output += `<i class="fa-regular fa-heart fa-xl"></i>`;
			}
			output += `</button>`;

			output += `<div style="margin-right:5px"><a href="${root}/profile?mID=${pet.member.mID}">${pet.member.memberDetail.name}</a></div>`;
			output += `</div></div></div></div>`;
		}


		petHome.innerHTML = output;
	}

	// 渲染分頁按鈕
	function renderPage(paginatedPetInfo) {
		let output = "";

		output += `<ul class="pagination justify-content-center">`;
		let disabled = paginatedPetInfo.number == 0 ? "disabled" : "";
		output += `<li class="page-item ${disabled}">`;
		output += `<button class="page-link">＜</button>`;

		output += `</li>`;
		for (let i = 1; i <= paginatedPetInfo.totalPages; i++) {
			let active = i == (paginatedPetInfo.number + 1) ? "active" : "";
			output += `<li class="page-item ${active}"><button class="page-link">${i}</button></li>`;
		}

		disabled = paginatedPetInfo.number == (paginatedPetInfo.totalPages - 1) ? "disabled" : "";
		output += `<li class="page-item ${disabled}">`;

		output += `<button class="page-link">＞</button>`;

		output += `</li>`;
		output += `</ul>`;
		pageHome.innerHTML = output;
	}

	// 切換分頁
	pageHome.addEventListener("click", function (e) {

		if (e.target.tagName !== "BUTTON") { return; }

		let page = e.target.innerText;
		page = (page == "＜") ? parseInt(pageHome.getElementsByClassName("active")[0].innerText) - 1 : page;
		page = (page == "＞") ? parseInt(pageHome.getElementsByClassName("active")[0].innerText) + 1 : page;

		getPetList(page, per.value);
	})

	// 切換每頁顯示數量
	per.addEventListener("input", function (e) {
		getPetList(1, per.value);
	})


	// 點讚
	petHome.addEventListener("click", function (e) {
		if (e.target.tagName !== "I") { return; }

		let pID = e.target.parentElement.getAttribute("pID");
		let url = `${root}/likePet?pID=${pID}`;

		fetch(url).then(result => result.text()).then(status => {
			if (status == "liked") {
				e.target.classList.remove("fa-regular");
				e.target.classList.add("fa-solid");
				e.target.style.color = "#e51515";
			}
			if (status == "unLiked") {
				e.target.classList.remove("fa-solid");
				e.target.classList.add("fa-regular");
				e.target.style.color = "";

			}
		})
	})

	//點到<button>，觸發<i>
	petHome.addEventListener("click", function (e) {
		if (e.target.tagName !== "BUTTON") { return; }
		e.target.children[0].click();
	})




}