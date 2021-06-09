import {  Navbar, Nav } from 'react-bootstrap'


const NavBar = () => {

    return (
        <>
    <Navbar className="styleNav"  expand="lg">
        <Navbar.Brand href="#home" id="TaurusLogo">TAURUS</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="ml-auto">
            
            <Nav.Link href="/reg-search">Registration Lookup</Nav.Link>
            <Nav.Link href="/incident-search">Incident Lookup</Nav.Link>
            
            
            
            </Nav>

          
            
        </Navbar.Collapse>
</Navbar>

</>
    )
}

export default NavBar;