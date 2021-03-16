import React from 'react';
import DefaultService from '../services/DefaultService';

class HelloComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            user: []
        }
    }

    componentDidMount(){

        DefaultService.getHelloMessage().then((response) => {
            this.setState({
                user: response.data
            })
        });
    }

    render (){
        return(
            <div>
                {`Hello ${this.state.user.email}`}
            </div>
        )
    }
}

export default HelloComponent