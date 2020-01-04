import React, { useState } from 'react'
import { fetchApiCreateUser } from './FetchApi';
import { Redirect } from 'react-router-dom';

interface AppProps{
    authentication :boolean;
    setAuthentication: (id:boolean) => void;
}

export function CreateUser(props :AppProps) :JSX.Element{

    const [usernameValue,setUsernameValue] = useState("");
    const [passwordValue,setPasswordValue] = useState("");
    const [userCreated,setUserCreated] = useState<Boolean>();

    async function handleSubmit(event :any){ 
        event.preventDefault();
        
        let url :string = "http://localhost:8080/user/add";

        const formData = new FormData();
        formData.append("username",usernameValue);
        formData.append("password",passwordValue);

        const response :any = await fetchApiCreateUser(url,formData);

        if (response === true){
            setUserCreated(true);
            props.setAuthentication(true)
        } else {
            setUserCreated(false);
        }
    }
    if(userCreated){
        return <Redirect to="./books"/>

    } else if(!userCreated){
        return (
            <div> Failed to Create User
                <form onSubmit={handleSubmit}>
                    <input type="text" value={usernameValue} onChange={event => setUsernameValue(event.target.value)}/>
                    <input type="text" value={passwordValue} onChange={event => setPasswordValue(event.target.value)}/>
                    <input type="submit" value ="Create User"/>
                </form>
            </div>
    );
    } 
    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={usernameValue} onChange={event => setUsernameValue(event.target.value)}/>
            <input type="text" value={passwordValue} onChange={event => setPasswordValue(event.target.value)}/>
            <input type="submit" value ="Create User"/>
        </form>
        );
}
