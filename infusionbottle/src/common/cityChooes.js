//引入省市区数据json文件
// import provinces from '../../static/address/provinces.json';
// import cities from '../../static/address/cities.json';
// import areas from '../../static/address/areas.json';


export function zmGetProvincesArr(provinces) {

  let provinceArr = [];
  provinces.forEach(function (item) {
    let obj = {};
    obj.name = item.item;
    obj.id = item.id;
    provinceArr.push(obj);
  });
  return provinceArr;
}

export function zmGetCitiesArr(provinceCode,cities) {
//   console.log("省：" + provinceCode);
  if (!provinceCode){
    provinceCode = '4'
  }
  let cityArr = [];
  cities.forEach(function (item) {
    if (item.provinceid == provinceCode) {
      let obj = {};
      obj.name = item.item;
      obj.id = item.id;
      obj.provinceCode = item.provinceid;
      cityArr.push(obj);
    }
  });
  return cityArr;
}

export function zmGetAreaArr(cityCode,areas) {
//   console.log("市：" + cityCode);
  if (!cityCode){
    cityCode = '4'
  }
  let areaArr = [];
  areas.forEach(function (item) {
    if (item.cityid == cityCode) {
      let obj = {};
      obj.name = item.item;
      obj.id = item.id;
      obj.provinceCode = item.provinceid;
      obj.cityCode = item.cityid;
      areaArr.push(obj);
    }
  });
  return areaArr;
}