import {useRef, useState, useEffect} from 'react';
import * as StompJs from '@stomp/stompjs';

function CreateReadChat() {
    const [chatList, setChatList] = useState([]);
    const [chat, setChat] = useState('');

    const client = useRef({});
    const channelId = 1;
    const accessToken = "Bearer eyJxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"

    const connect = () => {
        client.current = new StompJs.Client({
            brokerURL: 'ws://localhost:8080/ws/chat',
            connectHeaders: {
                'Authorization': accessToken,
            },
            onConnect: () => {
                console.log('success');
                subscribe();
            },
        });
        client.current.activate();
    };

    const publish = (chat) => {
        if (!client.current.connected) return;

        client.current.publish({
            destination: '/pub/chat',
            body: JSON.stringify({
                "detail" : {
                    channelId: channelId,
                    message: chat,
                }
            }),
        });

        setChat('');
    };

    const subscribe = () => {
        client.current.subscribe('/sub/chat/' + channelId, (body) => {
            console.log('Received message:', body); // 메시지를 콘솔에 출력
            const json_body = JSON.parse(body.body);
            setChatList((_chat_list) => [
                ..._chat_list, json_body
            ]);
        });
    };

    const disconnect = () => {
        client.current.deactivate();
    };

    const handleChange = (event) => { // 채팅 입력 시 state에 값 설정
        setChat(event.target.value);
    };

    const handleSubmit = (event, chat) => { // 보내기 버튼 눌렀을 때 publish
        event.preventDefault();
        publish(chat);
    };

    useEffect(() => {
        connect();
        return () => disconnect();
    }, []);

    return (
        <div>
            <div className={'chat-list'}>{chatList.map((chat, index) => {
                return (
                    <div> 보낸 유저 : {chat.detail.userId} / 내용 : {chat.detail.message} </div>
                )
            })}
            </div>
            <form onSubmit={(event) => handleSubmit(event, chat)}>
                <div>
                    <input type={'text'} name={'chatInput'} onChange={handleChange} value={chat}/>
                </div>
                <input type={'submit'} value={'의견 보내기'}/>
            </form>
        </div>
    );
}

export default CreateReadChat;