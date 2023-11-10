// 로그인 상태를 저장하는 변수

window.onload = function () {
  httpRequest1 = sendRequest("/pass_project/WebContent/assets/data/festival.xml", null, festivals, "GET");


  var isLoginCheck = localStorage.getItem("isLogin");
  if (isLoginCheck === "true") {
    updateHeader(true);
  } else {
    updateHeader(false);
  }
}
function festivals() {
  // 서버로부터 data를 전부 받은 상태 (Completed)
  if (httpRequest1.readyState == 4) {
    // 서버로부터의 응답 상태 (OK)
    if (httpRequest1.status == 200) {
      // 서버에서 응답 온 data를 XML로 받음.
      var festivals = httpRequest1.responseXML;
      // xml에서 책목록을 배열로 받음.
      var festivallist = festivals.querySelectorAll("festival");
      var len = festivallist.length;
      var festivalView = "";
      for (var i = 0; i < len; i++) {
        var festival = festivallist[i];
        var isbn = festival.querySelector("isbn").childNodes[0].nodeValue; // xml에서 isbn 얻기
        var title = festival.querySelector("title").childNodes[0].nodeValue; // xml에서 제목 얻기

        festivalView += ' <div class="card col-lg-3 col-md-6 col-sm-12">';
        festivalView += '   <img class="card-img-top" src="./assets/img/' + isbn + '.png" alt="Card image" />';
        festivalView += ' <div class="mt-1 p-1 text-center">';
        festivalView += '<h4 class="card-title">' + title + '</h4><a href="#" class="btn btn-outline-success btn-small">더보기</a>';
        festivalView += " </div>";
        festivalView += "</div>";
      }

      document.querySelector("#plist").innerHTML = festivalView;
    }
  }
}

function updateHeader(isLoginCheck) {
  var headerNavConfirmOff = document.getElementById('header_nav_confirm_off');
  var headerNavConfirmOn = document.getElementById('header_nav_confirm_on');
    if (isLoginCheck==true) {
      // 로그인 상태일 때 헤더 업데이트
      headerNavConfirmOff.style.display = 'none';
      headerNavConfirmOn.style.display = 'flex';
    } else {
      // 로그아웃 상태일 때 헤더 업데이트
      headerNavConfirmOff.style.display = 'flex';
      headerNavConfirmOn.style.display = 'none';
    }
  }
	
function login() {
  // 사용자 정보를 입력받는다.
  var userid = document.getElementById('userid').value;
  var userpass = document.getElementById('userpwd').value;

  if (userid == "ssafy" && userpass == "1234") {
    localStorage.setItem("isLogin", true);
    // 로그인 성공 시 메인 페이지로 이동
    alert("로그인 되었습니다.");
    document.location.reload(); // 부모창 새로고침.
  $("#login").modal("hide"); // 모달창 닫기.

  } else {
    alert("아이디 또는 비밀번호 확인!!!");
    return;
  }
}

// 로그아웃 함수
function logout() {
  localStorage.setItem("isLogin", false);
  updateHeader();
}

// 비밀번호 찾기
function findPwd() {
  localStorage.setItem("isLogin", false);
  updateHeader();
}


// 시도 선택
function populateSido() {
  var selectSido = document.getElementById("sido");
  selectSido.innerHTML = "<option value=''>시도선택</option>";

  for (var i = 0; i < data.length; i++) {
    var option = document.createElement("option");
    option.value = data[i].name;
    option.text = data[i].name;
    selectSido.appendChild(option);
  }
}

// 구군 선택
function populateGugun() {
  var selectSido = document.getElementById("sido");
  var selectGugun = document.getElementById("gugun");
  selectGugun.innerHTML = "<option value=''>구군선택</option>";

  var selectedSido = selectSido.value;
  var subregions = findSubregions(selectedSido);

  for (var i = 0; i < subregions.length; i++) {
    var option = document.createElement("option");
    option.value = subregions[i].name;
    option.text = subregions[i].name;
    selectGugun.appendChild(option);
  }
}

// 동 선택
function populateDong() {
  var selectGugun = document.getElementById("gugun");
  var selectDong = document.getElementById("dong");
  selectDong.innerHTML = "<option value=''>동선택</option>";

  var selectedSido = document.getElementById("sido").value;
  var selectedGugun = selectGugun.value;
  var dongList = findDong(selectedSido, selectedGugun);

  for (var i = 0; i < dongList.length; i++) {
    var option = document.createElement("option");
    option.value = dongList[i];
    option.text = dongList[i];
    selectDong.appendChild(option);
  }
}

// 선택한 시도에 대한 하위 지역 정보 찾기
function findSubregions(selectedSido) {
  for (var i = 0; i < data.length; i++) {
    if (data[i].name === selectedSido) {
      return data[i].subregions;
    }
  }
  return [];
}

// 선택한 시도 및 구군에 대한 동 정보 찾기
function findDong(selectedSido, selectedGugun) {
  var subregions = findSubregions(selectedSido);
  for (var i = 0; i < subregions.length; i++) {
    if (subregions[i].name === selectedGugun) {
      return subregions[i].dong;
    }
  }
  return [];
}

// 시도가 변경될 때 구군과 동 정보를 업데이트
document.getElementById("sido").addEventListener("change", populateGugun);
document.getElementById("gugun").addEventListener("change", populateDong);

// 초기 시도 정보 로드
populateSido();

function btnclick() {
  // 검색어 입력란에서 검색어를 가져옵니다.
  var keyword = document.getElementById("keyword").value;

  if (!keyword.replace(/^\s+|\s+$/g, "")) {
    alert("키워드를 입력해주세요!");
    return;
  }

  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
  ps.keywordSearch(keyword, function (data, status) {
    if (status === kakao.maps.services.Status.OK) {
      // 검색 결과가 있을 경우 첫 번째 검색 결과의 위도와 경도 정보를 가져옵니다.
      var lat = data[0].y;
      var lng = data[0].x;

      // 가져온 위도와 경도 정보를 사용하여 지도의 중심을 이동합니다.
      var moveLatLon = new kakao.maps.LatLng(lat, lng);
      map.setCenter(moveLatLon);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      alert("검색 결과가 존재하지 않습니다.");
    } else if (status === kakao.maps.services.Status.ERROR) {
      alert("검색 결과 중 오류가 발생했습니다.");
    }
  });
}

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {
  var keyword = document.getElementById("keyword").value;

  if (!keyword.replace(/^\s+|\s+$/g, "")) {
    alert("키워드를 입력해주세요!");
    return false;
  }

  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
  ps.keywordSearch(keyword, placesSearchCB);
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
  if (status === kakao.maps.services.Status.OK) {
    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다
    displayPlaces(data);

    // 페이지 번호를 표출합니다
    displayPagination(pagination);
  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    alert("검색 결과가 존재하지 않습니다.");
    return;
  } else if (status === kakao.maps.services.Status.ERROR) {
    alert("검색 결과 중 오류가 발생했습니다.");
    return;
  }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {
  var listEl = document.getElementById("placesList"),
    menuEl = document.getElementById("menu_wrap"),
    fragment = document.createDocumentFragment(),
    bounds = new kakao.maps.LatLngBounds(),
    listStr = "";

  // 검색 결과 목록에 추가된 항목들을 제거합니다
  removeAllChildNods(listEl);

  // 지도에 표시되고 있는 마커를 제거합니다
  removeMarker();

  for (var i = 0; i < places.length; i++) {
    // 마커를 생성하고 지도에 표시합니다
    var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
      marker = addMarker(placePosition, i),
      itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(placePosition);

    // 마커와 검색결과 항목에 mouseover 했을때
    // 해당 장소에 인포윈도우에 장소명을 표시합니다
    // mouseout 했을 때는 인포윈도우를 닫습니다
    (function (marker, title) {
      kakao.maps.event.addListener(marker, "mouseover", function () {
        displayInfowindow(marker, title);
      });

      kakao.maps.event.addListener(marker, "mouseout", function () {
        infowindow.close();
      });

      itemEl.onmouseover = function () {
        displayInfowindow(marker, title);
      };

      itemEl.onmouseout = function () {
        infowindow.close();
      };
    })(marker, places[i].place_name);

    fragment.appendChild(itemEl);
  }

  // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
  listEl.appendChild(fragment);
  menuEl.scrollTop = 0;

  // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
  map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
  var el = document.createElement("li"),
    itemStr =
      '<span class="markerbg marker_' +
      (index + 1) +
      '"></span>' +
      '<div class="info">' +
      "   <h5>" +
      places.place_name +
      "</h5>";

  if (places.road_address_name) {
    itemStr +=
      "    <span>" +
      places.road_address_name +
      "</span>" +
      '   <span class="jibun gray">' +
      places.address_name +
      "</span>";
  } else {
    itemStr += "    <span>" + places.address_name + "</span>";
  }

  itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

  el.innerHTML = itemStr;
  el.className = "item";

  return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
    imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
    imgOptions = {
      spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
      spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
      offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
    },
    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
    marker = new kakao.maps.Marker({
      position: position, // 마커의 위치
      image: markerImage,
    });

  marker.setMap(map); // 지도 위에 마커를 표출합니다
  markers.push(marker); // 배열에 생성된 마커를 추가합니다

  return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
  }
  markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
  var paginationEl = document.getElementById("pagination"),
    fragment = document.createDocumentFragment(),
    i;

  // 기존에 추가된 페이지번호를 삭제합니다
  while (paginationEl.hasChildNodes()) {
    paginationEl.removeChild(paginationEl.lastChild);
  }

  for (i = 1; i <= pagination.last; i++) {
    var el = document.createElement("a");
    el.href = "#";
    el.innerHTML = i;

    if (i === pagination.current) {
      el.className = "on";
    } else {
      el.onclick = (function (i) {
        return function () {
          pagination.gotoPage(i);
        };
      })(i);
    }

    fragment.appendChild(el);
  }
  paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
  var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

  infowindow.setContent(content);
  infowindow.open(map, marker);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
  while (el.hasChildNodes()) {
    el.removeChild(el.lastChild);
  }
}
