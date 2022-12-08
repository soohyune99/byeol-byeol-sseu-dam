/* communityMain.html */



let communityService = (function(){
    function getTopViewList(board, callback, error){
        $.ajax({
            url: "/board/topview",
            type: "get",
            success: function(topViews, status, xhr){
                if(callback){
                    callback(topViews);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getBoardList(board, callback, error){
        $.ajax({
            url: "/board",
            type: "get",
            success: function(boards, status, xhr){
                if(callback){
                    callback(boards);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getCategoryBoards(boardCategory, callback, error){
        $.ajax({
            url: "/board/" + boardCategory,
            type: "get",
            success: function(boards, status, xhr){
                if(callback){
                    callback(boards);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getSearchBoards(keyword, callback, error){
        $.ajax({
            url: "/board/" + keyword,
            type: "post",
            success: function(boards, status, xhr){
                if(callback){
                    callback(boards);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getBoardDetail(boardId, callback, error){
        $.ajax({
            url: "/board/read/" + boardId,
            type: "get",
            success: function(board, status, xhr){
                if(callback){
                    callback(board);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function saveBoard(formData, callback, error){
        $.ajax({
            url: "/board/write",
            type: "post",
            data: formData,
            enctype:'multipart/form-data',
            cache:false,
            contentType:false,
            processData:false,
            success: function(status, xhr){
                if(callback){
                    callback();
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function updateBoard(formData, callback, error){
        $.ajax({
            url: "/board/update/" + boardId,
            type: "post",
            data: formData,
            enctype:'multipart/form-data',
            cache:false,
            contentType:false,
            processData:false,
            success: function(boardId, status, xhr){
                if(callback){
                    callback(boardId);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function deleteBoard(boardId, callback, error){
        $.ajax({
            url: "/board/" + boardId,
            type: "delete",
            success: function(status, xhr){
                if(callback){
                    callback();
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function plusBoardView(boardDTO, callback, error){
        $.ajax({
            url: "/board/view/" + boardDTO.boardId,
            type: "patch",
            data: JSON.stringify(boardDTO),
            contentType: "application/json; charset=utf-8",
            success: function(boardView, status, xhr){
                if(callback){
                    callback(boardView);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function infiniteScroll(formData, callback, error){
        $.ajax({
            url: "/board/scroll/",
            type: "post",
            data:formData,
            enctype:'multipart/form-data',
            cache:false,
            contentType:false,
            processData:false,
            success: function(boards, status, xhr){
                if(callback){
                    callback(boards);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금전';
        if (betweenTime < 60) {
            return `${betweenTime}분전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년전`;
    }

    function uploadBoardFile(file, callback, error){
        var data = new FormData();
        data.append("file", file);

        $.ajax({
            url: "/board/upload",
            type: "post",
            data: data,
            enctype: 'multipart/form-data',
            cache: false,
            contentType: false,
            processData: false,
            success: function(file, status, xhr){
                if(callback){
                    callback(file);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    return { getTopViewList:getTopViewList, getBoardList:getBoardList, getCategoryBoards:getCategoryBoards,
        getSearchBoards:getSearchBoards, getBoardDetail:getBoardDetail, saveBoard:saveBoard, deleteBoard:deleteBoard,
        updateBoard:updateBoard, plusBoardView:plusBoardView, uploadBoardFile:uploadBoardFile,
        infiniteScroll:infiniteScroll, timeForToday:timeForToday }
})();

