<%@ tag language="java" pageEncoding="UTF-8"%>

<div class="navbar navbar-inverse">
	<div class="navbar-inner" style="padding-top: 10px;">
        <a class="brand" href="/" style="margin-left: 20px">Funreco</a>
        <form style="float:right;" action="/logout">
            <button type="submit" class="btn btn-inverse">
                <i class="icon-white icon-off"></i>&nbsp;&nbsp;Logout
            </button>
        </form>
        <form class="navbar-form" action="/searchProfile">
			<input type="text" name="email" class="input-large"
				placeholder="Email..."> <input type="text" name="facebookId"
				class="input-large" placeholder="Facebook ID...">
			<button type="submit" class="btn">
				<i class="icon-search"></i>&nbsp;&nbsp;Search profile
			</button>
		</form>
	</div>
</div>

