var reserveData;
var trIndex = 0;

var tdSet = '';
$(document).ready(function() {
  $.ajax({
    url: 'http://127.0.0.1/rooms',
    success: function(result) {
      var tableHtml = '';
      $.each(result, function(i, d){
        $("#roomSelectBox").append("<option value='"+d.id+"'>"+d.name+"</option>");
        tableHtml += '<th>'+d.name+'</th>';
        tdSet += '<td class="room_'+d.id+'"></td>'
      });

      $('#reserveTable thead').html(tableHtml);
      $('#reserveTable tbody').html('<tr id="tr_'+trIndex+'">' + tdSet + '</tr>');

      $.ajax({
        url: 'http://127.0.0.1/reserves/dates',
        success: function(result) {
          reserveData = result;
          $.each(reserveData, function(i){
            $("#reserveSelectBox").prepend("<option value='"+i+"'>"+i+"</option>");
          });
          $("#reserveSelectBox").val($("#reserveSelectBox option:first").val());
          setTable();
        },
        error: function() {
          alert("시스템 사정으로 요청하신 내용을 처리할수 없음");
          return false;
        }
      });
    },
    error: function() {
      alert("시스템 사정으로 요청하신 내용을 처리할수 없음");
      return false;
    }
  });

  $("#reserveSelectBox").change(function() {
    setTable();
  });

  $("form").submit(function() {
    event.preventDefault();
    // time format 체크
    if(setHHMMSSto($('#end').val(), ":") - setHHMMSSto($('#start').val(), ":") <= 0) {
      alert('종료시간은 시작시간보다 커야합니다.');
      return false;
    }
    if(!isIntervalTime($('#start').val(), ":") || !isIntervalTime($('#end').val(), ":")) {
      alert('시간은 30분 단위로 입력하세요.');
      return false;
    }
    // JSON으로 요청
    var data = {};
    $.each( $(this).serializeArray(), function(index, o){
      data[o.name] = o.value
    });

    $.ajax({
      method: 'POST',
      url: 'http://127.0.0.1/reserves',
      data: JSON.stringify(data),
      dataType: 'json',
      contentType: "application/json",
      success: function(result) {
        console.log(result);
        alert("등록 되었습니다.");
      },
      error: function() {
        alert("시스템 사정으로 요청하신 내용을 처리할수 없음");
      }
    });
    return false;
  });

  $('.datepicker').datepicker({
    dateFormat: 'yy-mm-dd'
    ,appendText: "(yyyy-mm-dd)"
    ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
    ,changeMonth: true //콤보박스에서 월 선택 가능
    ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
    ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
    ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
    ,minDate: "-0M"
  });

  $('.timepicker').timepicker({
    timeFormat: 'HH:mm:ss',
    interval: 30,
    minTime: '10',
    maxTime: '19',
    defaultTime: '10',
    startTime: '10:00',
    dynamic: false,
    dropdown: true,
    scrollbar: true
  });
});

var setTable = function() {
  var renderKey = $("#reserveSelectBox").val();
  trIndex = 0;
  $("#reserveTable tbody tr").not("#tr_0").remove();

  $.each(reserveData[renderKey], function(i, v){
    var trTime = v.startTime.slice(0, 2);
    if(parseInt(trTime, 10) !== parseInt(trIndex, 10)) {
      var addTr = '<tr id="tr_'+trTime+'">'+tdSet+'</tr>';
      $('#reserveTable tbody tr:last').after(addTr);
      trIndex = trTime;
    }
    var reserveTxt = v.repeat >= 1 ? '(반복 '+v.repeat+'회) <br />' : '';
    reserveTxt += v.userName + '<br />' + v.startTime + '<br />' + v.endTime;
    $("#tr_"+trIndex).find('.room_'+ v.roomId).html(reserveTxt);
  });
};

var isIntervalTime = function(str, spliter) {
  var arrStr = str.split(spliter);

  if(arrStr[1] % 30 >= 0) {
    return false;
  } else {
    return true;
  }
};

var setHHMMSSto = function (str, spliter) {
  var arrStr = str.split(spliter);

  var date = new Date();
  date.setHours(arrStr[0]);
  date.setMinutes(arrStr[1]);
  date.setSeconds(arrStr[2]);

  return date;
};