window.onload = () => {
	const petHome = document.getElementById("petHome");
	const pageHome = document.getElementsByClassName("pageHome")[0];
	const per = document.getElementById("per");
	let loggedInMemberID;

	(function init() {
		getLoggedInMemberID();
		getPetList();
	})()

	function getLoggedInMemberID() {
		fetch(`${root}/GetLoggedInMemberID`).then(result => result.text()).then(memberID => {
			loggedInMemberID = parseInt(memberID)
			console.log(loggedInMemberID)
		})
	}

	function getPetList(page = 1, count = 6) {
		let url = `${root}/GetPets?page=${page}&count=${count}`;
		fetch(url).then(result => result.json()).then(paginatedPetInfo => {
			console.log(paginatedPetInfo);

			renderPetList(paginatedPetInfo.content);
			renderPage(paginatedPetInfo);
		})
	}

	function renderPetList(petList) {
		let output = "";
		for (let pet of petList) {
			output += `<div class="col">`;
			output += `<div class="card shadow-sm">`;
			output += `<img src="${root}/getPetPhoto?pID=${pet.pID}" class="w-100"`;
			output += `<div class="card-body">`;
			output += `<p class="card-text fs-3">${pet.name}</p>`;
			output += `<div class="d-flex justify-content-between align-items-center">`;


			output += `<button type="button" class="btn btn-sm btn-outline-secondary">`;
			if (pet.likedByMemberIds.includes(loggedInMemberID)) {
				output += `<i class="fa-solid fa-thumbs-up"></i>`;
			} else {
				output += `<i class="fa-regular fa-thumbs-up"></i>`;
			}

			output += `</button>`;

			output += `<div style="margin-right:5px"><a href="${root}/profile?mID=${pet.member.mID}">${pet.member.memberDetail.name}</a></div>`;
			output += `</div></div></div></div>`;
		}


		petHome.innerHTML = output;
	}

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

	pageHome.addEventListener("click", function (e) {

		if (e.target.tagName !== "BUTTON") { return; }

		let page = e.target.innerText;
		page = (page == "＜") ? parseInt(pageHome.getElementsByClassName("active")[0].innerText) - 1 : page;
		page = (page == "＞") ? parseInt(pageHome.getElementsByClassName("active")[0].innerText) + 1 : page;

		getPetList(page, per.value);
	})

	per.addEventListener("input", function (e) {
		getPetList(1, per.value);
	})
}