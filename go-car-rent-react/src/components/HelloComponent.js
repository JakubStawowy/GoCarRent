import React from 'react';
import DefaultService from '../services/DefaultService';

class HelloComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            users: []
        }
    }

    componentDidMount(){

        DefaultService.getHelloMessage().then((response) => {
            this.setState({
                users: response.data
            })
        });
    }

    render (){
        return(
            <div>
                
                Hello 
                {
                    this.state.users.map(
                        user => user.email
                    )
                } 

            </div>
        )
    }
}

export default HelloComponent