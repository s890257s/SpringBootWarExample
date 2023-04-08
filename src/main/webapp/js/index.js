window.onload = () => {
	const petHome = document.getElementById("petHome");

	(function init() {
		getPetList();
	})()

	function getPetList(page = 1, count = 20) {
		let url = `${root}/GetPets?page=${page}&count=${count}`;

		fetch(url).then(result => result.json()).then(paginatedPetInfo => {
			renderPetList(paginatedPetInfo.content);
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
			output += `<i class="fa-regular fa-thumbs-up"></i>`;
			output += `</button>`;
			output += `<div style="margin-right:5px"><a href="${root}/profile?mID=${pet.member.mID}">${pet.member.memberDetail.name}</a></div>`;
			output += `</div></div></div></div>`;
		}

		petHome.innerHTML = output;
	}
}