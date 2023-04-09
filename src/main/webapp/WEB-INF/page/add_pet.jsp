<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>${webName}</title>
  </head>

  <jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

  <!-- croppie -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.5/croppie.js"
    integrity="sha512-vUJTqeDCu0MKkOhuI83/MEX5HSNPW+Lw46BA775bAWIp1Zwgz3qggia/t2EnSGB9GoS2Ln6npDmbJTdNhHy1Yw=="
    crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.5/croppie.css"
    integrity="sha512-2eMmukTZtvwlfQoG8ztapwAH5fXaQBzaMqdljLopRSA0i6YKM8kBAOrSSykxu9NN9HrtD45lIqfONLII2AFL/Q=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />


  <body>
    <jsp:include page="/WEB-INF/component/header.jsp" />
    <main class="m-5">
      <div class="container">
        <div class="row">
          <div class="col text-center">
            <form>
              <div class="mb-3">
                <label>寵物名字：<input type="text" name="petName" class="form-control"></label>
              </div>
              <div class="mb-3">
                <label>寵物年齡：<input type="number" name="petAge" class="form-control w-100 " min="0"></label>
              </div>
              <div class="mb-3">
                <label>寵物種類： <input class="form-control" list="petTypeList" name="petType"> <datalist id="petTypeList">
                    <option value="dog">
                    <option value="cat">
                    <option value="bird">
                    <option value="fish">
                    <option value="rabbit">
                    <option value="hamster">
                    <option value="turtle">
                  </datalist>
              </div>
              <div class="mb-1">
                <label>寵物照片：<input type="file" name="photo" class="form-control" id="photoContent"></label>
              </div>
              <div class="mb-3" id="previewImgHome">
                <img src="${root}/getPetPhoto" id="preview" class="w-25">
              </div>
              <button class="btn btn-secondary" id="cancelAddBTN">取消</button>
              <button class="btn btn-primary" id="addPetBTN">新增</button>
            </form>
          </div>
        </div>
      </div>
    </main>
    <jsp:include page="/WEB-INF/component/footer.jsp" />
  </body>

  <script>
    let cropPreview;

    // 取消新增，返回上一頁
    $("#cancelAddBTN").click(function (e) {
      e.preventDefault();
      window.history.back();
    })

    // 裁切+預覽圖片
    $("#photoContent").change(function (e) {
      let file = e.target.files[0];

      // 如果沒選擇圖片，則刪除croppie-container，並顯示預設圖片
      if (file == undefined) {
        $(".croppie-container").remove();
        $("#previewImgHome").html("<img src='${root}/getPetPhoto' id='preview' class='w-25'>");
        cropPreview = undefined;
      }

      // 如果有選擇圖片，則呼叫croppie插件，並顯示預覽圖片
      if (file != undefined) {
        //避免重複產生
        $(".croppie-container").remove();
        $("#previewImgHome").html("<img src='${root}/getPetPhoto' id='preview' class='w-25'>");

        //呼叫croppie插件
        $("#preview").attr("src", URL.createObjectURL(file));
        cropPreview = $('#preview').croppie({
          viewport: {
            width: 200,
            height: 200,
          },
          boundary: {
            width: 300,
            height: 300
          }
        });
      }
    });

    // 點擊圖片即可上傳
    $("#previewImgHome").click(function (e) {
      if (e.target.id == "preview") {
        $("#photoContent").click();
      }
    })

    // 新增寵物按鈕事件
    $("#addPetBTN").click(function (e) {
      e.preventDefault();
      let name = $("input[name='petName']").val();
      let age = $("input[name='petAge']").val();
      let type = $("input[name='petType']").val();
      let photo;

      let formData = new FormData();
      formData.append("name", name);
      formData.append("age", age);
      formData.append("type", type);

      // 如果有選擇圖片，則將圖片轉成blob，並加入formData
      if (cropPreview != undefined) {
        cropPreview.croppie('result', "blob").then(function (blob) {
          formData.append("photoContent", blob);
          putPet(formData);
        });
      }

      // 如果沒有選擇圖片，則直接新增
      if (cropPreview == undefined) {
        putPet(formData);
      }
    })

    // 非同步新增寵物
    function putPet(formData) {
      console.log(formData)
      fetch("${root}/addPet", {
        method: "POST",
        body: formData
      })
        .then(function (response) {
          return response.text();
        })
        .then(function (status) {
          if (status == "success") {
            alert("新增成功");
            window.location.href = "${root}/profile";
          } else {
            alert("新增失敗");
          }
        })
    }


  </script>


  </html>