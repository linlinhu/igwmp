var winLstWrapEl = document.getElementsByClassName('win-lst-wrap')[0],
	nStartX='' ;
winLstWrapEl.addEventListener('touchstart', function (e) {
    nStartY = e.targetTouches[0].pageY;
    nStartX = e.targetTouches[0].pageX;
});
winLstWrapEl.addEventListener('touchmove', function (e) {
    nMoveX = (e.touches[0]).pageX;
    var marginLeft = winLstWrapEl.getAttribute('marginLeft');
    var lstLen = winLstWrapEl.children.length;
    marginLeft = parseInt(marginLeft?marginLeft:0);
    marginLeft+=( nMoveX-nStartX);
     if(lstLen<5){
    	marginLeft=0
    }
    if(lstLen==5){
    	if(marginLeft>0){
    	marginLeft=0
	    }else if(marginLeft<-310){
	    	marginLeft=-310
	    }
    }
    if(lstLen==6){
    	if(marginLeft>0){
    	marginLeft=0
	    }else if(marginLeft<-620){
	    	marginLeft=-620
	    }
    }
    
    winLstWrapEl.setAttribute('marginLeft', marginLeft);
    winLstWrapEl.setAttribute('style', 'margin-left: '+marginLeft+'px');
    /*console.log(nMoveX-nStartX,nStartX,nMoveX)*/
});
winLstWrapEl.addEventListener('touchend', function (e) {
    nChangY = e.changedTouches[0].pageY;
    nChangX = e.changedTouches[0].pageX;
	var lstLen = winLstWrapEl.children.length;
	if(lstLen>4){
        var swiperDistance = nStartX-nChangX;
        var marginLeft = winLstWrapEl.getAttribute('marginLeft');
        marginLeft = parseInt(marginLeft?marginLeft:0);
        if(swiperDistance>0){//向左滑动
	        if(swiperDistance<310){
	        	marginLeft = -310;
	        }
	        if(swiperDistance>=310){
	        	marginLeft = -620;
	        }
        }else{
        	if(marginLeft<0){
        		if(swiperDistance>-310){
        			marginLeft = marginLeft+310<=0?marginLeft+310:0;
        		}else{
        			marginLeft = marginLeft+620<=0?marginLeft+620:0;
        		}
        		
        	}
        }
    	winLstWrapEl.setAttribute('marginLeft', marginLeft);
    	winLstWrapEl.setAttribute('style', 'margin-left: '+marginLeft+'px');
    
   	}
});