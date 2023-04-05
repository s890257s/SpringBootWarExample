window.onload = () => {
	const petHome = document.getElementById("petHome");

	(function init() {
		getPetList();
	})()

	function getPetList(page = 1, count = 20) {
		let url = `${root}/getPets?page=${page}&count=${count}`;

		fetch(url).then(result => result.json()).then(paginatedPetInfo => {
			console.log(paginatedPetInfo.content);
			renderPetList(paginatedPetInfo.content);
		})

	}

	function renderPetList(petList) {
		let output = "";
		for (let pet of petList) {
			output += `<div class="col">`;
			output += `<div class="card shadow-sm">`;
			output += `<img src="data:image/png;base64,${pet.photo}" class="w-100"`;
			output += `<div class="card-body">`;
			output += `<p class="card-text">${pet.name}</p>`;
			output += `<div class="d-flex justify-content-between align-items-center">`;
			output += `<button type="button" class="btn btn-sm btn-outline-secondary">`;
			output += `<i class="fa-regular fa-thumbs-up"></i>`;
			output += `</button>`;
			output += `</div></div></div></div>`;
		}

		petHome.innerHTML = output;
	}
}