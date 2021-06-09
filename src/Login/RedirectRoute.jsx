import { Redirect } from 'react-router-dom'
import React from 'react'


class RedirectRoute extends React.Component {



    render() {
        const AccessablePage = this.props.component;
        

        return (
            <AccessablePage />
        ) ;
    }
}

export default RedirectRoute;