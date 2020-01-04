import React, { useState } from 'react'
import { fetchApiCreateUser } from './FetchApi';
import { Redirect } from 'react-router-dom';

interface AppProps{
    authentication :boolean;
    setAuthentication: (id:boolean) => void;
}

export function Login(props :AppProps) :JSX.Element{

    const [usernameValue,setUsernameValue] = useState("");
    const [passwordValue,setPasswordValue] = useState("");

    async function handleSubmit(event :any){ 
        event.preventDefault();
        
        let url :string = "http://localhost:8080/user/checkUser";
        const formData = new FormData();
        formData.append("username",usernameValue);
        formData.append("password",passwordValue);


        const response :any = await fetchApiCreateUser(url,formData);
        if(await response === true){
            props.setAuthentication(true);
        } else if (await response === false){
            props.setAuthentication(false);
        }

    }
    if(props.authentication){
        return <Redirect to ="./books"/>
    } else {
        return (
                    <form onSubmit={handleSubmit}>
                        <input type="text" value={usernameValue} onChange={event => setUsernameValue(event.target.value)}/>
                        <input type="text" value={passwordValue} onChange={event => setPasswordValue(event.target.value)}/>
                        <input type="submit" value ="Login"/>
                    </form>
            );
    }

}
